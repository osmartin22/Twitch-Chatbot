package twitch4j_packages.twitch4j.modules;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;
import twitch4j_packages.twitch4j.TwitchClient;
import twitch4j_packages.twitch4j.modules.event.ModuleDisabledEvent;
import twitch4j_packages.twitch4j.modules.event.ModuleEnabledEvent;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

/**
 * Module Loader
 */
@Slf4j
public class ModuleLoader {

    public static final String MODULE_DIR = "modules";
    @Getter
    private static final List<Class<? extends IModule>> classModules = new CopyOnWriteArrayList<>();

    @Getter
    private final Map<String, ModulePair> modules = new LinkedHashMap<>();

    static {
        if (Configuration.LOAD_MODULES) {
            File modulesDir = new File(MODULE_DIR);
            if (modulesDir.exists()) {
                if (!modulesDir.isDirectory()) {
                    throw new RuntimeException(MODULE_DIR + " isn't a directory!");
                }
            } else {
                if (!modulesDir.mkdir()) {
                    throw new RuntimeException("Error creating " + MODULE_DIR + " directory");
                }
            }

            loadModulesFromDirectory(modulesDir);
        }
    }

    private final TwitchClient client;

    public ModuleLoader(TwitchClient client) {
        this.client = client;

        loadClassModules(false);
    }

    private void loadClassModules(boolean reloadAll) {
        if (reloadAll) modules.clear();
        classModules.forEach(clazz -> {
            try {
                IModule module = clazz.newInstance();
                ModulePair modulePair = new ModulePair(module, false);
                log.info("Loading module {}", module.getName(), module.getVersion(), module.getAuthor());
                if (Configuration.AUTOMATICALLY_ENABLE_MODULES) {
                    enableModule(module);
                } else addModule(module);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Unable to load module " + clazz.getName() + "!", e);
            }
        });
    }

    public void addModule(IModule module) {
        addModule(module, false);
    }

    private void addModule(IModule module, boolean active) {
        ModulePair pair = new ModulePair(module, active);
        String moduleName = module.getClass().getSimpleName();
        if (!modules.containsKey(moduleName))
            if (active) client.getEventManager().dispatchEvent(new ModuleEnabledEvent(module));
        modules.put(moduleName, pair);
    }

    public void enableModule(IModule module) {
        if (!modules.containsKey(module.getClass().getSimpleName()))
            addModule(module, true);
        else {
            enableModule(module.getClass().getSimpleName());
        }
    }

    private void enableModule(String name) {
        if (modules.containsKey(name)) {
            ModulePair pair = modules.get(name);
            if (!pair.isActive()) {
                client.getEventManager().dispatchEvent(new ModuleEnabledEvent(pair.getModule()));
                pair.getModule().enable(client);
                pair.setActive(true);
            }
        }
    }

    public void disableModule(String name) {
        if (modules.containsKey(name)) {
            ModulePair pair = modules.get(name);
            if (pair.isActive()) {
                client.getEventManager().dispatchEvent(new ModuleDisabledEvent(pair.getModule()));
                pair.getModule().disable();
                pair.setActive(false);
            }
        }
    }

    public void removeModule(String name) {
        if (modules.containsKey(name)) {
            disableModule(name);
            modules.remove(name);
        }
    }

    public void removeModule(IModule module) {
        removeModule(module.getClass().getSimpleName());
    }

    public void reloadMdoules() {
        classModules.clear();
        loadModulesFromDirectory(new File(MODULE_DIR));
        loadClassModules(true);
    }

    private static void loadModulesFromDirectory(File modulesDir) {
        File[] files = modulesDir.listFiles((FilenameFilter) FileFilterUtils.suffixFileFilter("jar"));
        if (files != null && files.length > 0) {
            log.info("Attempting to load {} external module(s)...", files.length);
            loadExternalModules(Arrays.asList(files));
        }
    }

    public static void loadExternalModules(List<File> files) {
        files.stream()
                .filter(file -> file.isFile() && file.getName().endsWith(".jar"))
                .forEach(ModuleLoader::loadExternalModules);
    }

    public static synchronized void loadExternalModules(File file) {
        if (file.isFile() && file.getName().endsWith(".jar")) {
            try (JarFile jar = new JarFile(file)) {
                Manifest mf = jar.getManifest();
                String moduleAttribute = mf.getMainAttributes().getValue("Twitch4J-ModuleClass");
                List<String> moduleClasses = new ArrayList<>();
                if (moduleAttribute != null) {
                    moduleClasses.addAll(Arrays.asList(moduleAttribute.replaceAll("\\s", "").split(";")));
                }

                URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                URL url = file.toURI().toURL();
                for (URL it : loader.getURLs()) {
                    if (it.equals(url)) return;
                }

                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(loader, url);

                if (moduleClasses.size() == 0) {
                    for (String clazz : listClasses(jar)) {
                        try {
                            Class classInstance = loadClass(clazz);
                            if (IModule.class.isAssignableFrom(classInstance) && !classInstance.equals(IModule.class)) {
                                addModuleClass(classInstance);
                            }
                        } catch (NoClassDefFoundError ignored) {
                        }
                    }
                } else {
                    for (String moduleClass : moduleClasses) {
                        log.info("Loading Class from Manifest Attribute: {}", moduleClass);
                        Class classInstance = loadClass(moduleClass);
                        if (IModule.class.isAssignableFrom(classInstance))
                            addModuleClass(classInstance);
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException | ClassNotFoundException e) {
                log.error("Unable to load module " + file.getName() + "!", e);
            }
        }
    }

    private static void addModuleClass(Class<? extends IModule> clazz) {
        if (!Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers()) && !classModules.contains(clazz)) {
            classModules.add(clazz);
        }
    }

    private static Class loadClass(String clazz) throws ClassNotFoundException {
        if (clazz.contains("$") && clazz.substring(0, clazz.lastIndexOf("$")).length() > 0) {
            try {
                loadClass(clazz.substring(0, clazz.lastIndexOf("$")));
            } catch (ClassNotFoundException ignored) {
            } // If the parent class doesn't exist then it is safe to instantiate the child
        }
        return Class.forName(clazz);
    }

    private static List<String> listClasses(JarFile jar) {
        return jar.stream()
                .filter(entry -> !entry.isDirectory() && entry.getName().endsWith(".class"))
                .map(path -> path.getName().replace("/", ".").substring(0, path.getName().length() - ".class".length()))
                .collect(Collectors.toList());
    }
}
