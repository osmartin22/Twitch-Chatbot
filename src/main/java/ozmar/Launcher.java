package ozmar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ozmar.commands.CalculatorConfig;
import ozmar.commands.CommandListConfig;
import ozmar.features.OnCommandReceivedConfig;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CalculatorConfig.class);
        context.register(CommandListConfig.class);
        context.register(OnCommandReceivedConfig.class);
        context.register(BotConfig.class);
        context.refresh();


//        Calculator calculator = context.getBean(Calculator.class);
//        calculator.setOperation("3+2");
//        System.out.println(calculator.compute());
//
//        EventUser user = new EventUser(Long.valueOf("142455120"), "namedauto");
//        Set<CommandPermission> s = new HashSet<>();
//        s.add(CommandPermission.EVERYONE);
//        CommandEvent commandEvent = new CommandEvent(CommandSource.CHANNEL, "namedAuto", user,
//                "!calc", "5+15", s);
//
//        CommandList commandList = context.getBean(CommandList.class);
//        commandList.setCommandEvent(commandEvent);
//        System.out.println(commandList.decideCommand());
//
//        OnCommandReceived onCommandReceived = context.getBean(OnCommandReceived.class);
//        onCommandReceived.onCommand(commandEvent);

        Bot bot1 = context.getBean(Bot.class);
        bot1.registerFeatures();
        bot1.start();

//        Bot bot = new Bot();
//        bot.registerFeatures();
//        bot.start();


        // !SpiritAnimal    You are an eagle
        // !robDab      "Robdab type of quotes"
        // !rateme      10 come here billyReady


// PepePls  Clap  KKomrade  KKool  gachiBASS lulWut  Clap2  AYAYA  HYPERLUL  billyReady  pepoS  theCHAMP  POGGERS  monkaGIGA monkaSHAKE  TriKool  PepeHands  HYPERLULWUT  BAOMN  RapThis  ppHop  peepoWink  monkaHmm PepoCheer  EZFingerGuns  pepeD  ZOINKS  pepeBASS  pepeJAM  FeelsLateMan  HYPEROMEGAZOINKS  HYPERCLAP nutTasty  OMEGALAUGHING  HACKERMANS  HYPERCLAP2  BLAPBLAP(->)  (<-)BLAAP  HYPEROMEGAPOGGERSCRAZY  POGPLANT  robDab Omegalol  FeelsAyayaMan  PepeSpin  PEEPERS  HYPERROBDAB  RareMoon  peepoT  moon2BASS
    }
}
