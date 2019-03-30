package twitch4j_packages.twitch4j.modules.event;

import twitch4j_packages.twitch4j.modules.IModule;

/**
 * Module Enabled Event
 */
public class ModuleEnabledEvent extends ModuleEvent {

    /**
     * Constructor
     *
     * @param module Module
     */
    public ModuleEnabledEvent(IModule module) {
        super(module);
    }
}
