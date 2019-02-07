package ozmar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ozmar.timers.ChatListTimer;
import ozmar.timers.WordCountTimer;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BotConfig.class);

        Bot bot = context.getBean(Bot.class);
        bot.initialize();
        bot.registerFeatures();
        bot.start();

        ChatListTimer chatListTimer = context.getBean(ChatListTimer.class);
        WordCountTimer wordCountTimer = context.getBean(WordCountTimer.class);
        chatListTimer.startTimer();
        wordCountTimer.startTimer();

//        Pokemon pokemon = Pokemon.getById(RandomHelper.getRandomNumberNotZero(807));
//        Nature nature = Nature.getById(RandomHelper.getRandomNumberNotZero(25));
//
//        String natureName = nature.getName();
//        String pokeName = StringUtils.capitalize(pokemon.getName());
//        String gender;
//        if(RandomHelper.getRandNum(1) == 1) {
//            gender = " male ";
//        } else {
//            gender = " female ";
//        }
//
//        System.out.println("Caught a " + natureName + gender + pokeName);

    }
}
//    PepePls  Clap  KKomrade  KKool  gachiBASS lulWut  Clap2  AYAYA  HYPERLUL
//    billyReady  pepoS  theCHAMP  POGGERS  monkaGIGA monkaSHAKE  TriKool  PepeHands
//    HYPERLULWUT  BAOMN  RapThis  ppHop  peepoWink  monkaHmm PepoCheer  EZFingerGuns  pepeD
//    ZOINKS  pepeBASS  pepeJAM  FeelsLateMan  HYPEROMEGAZOINKS  HYPERCLAP nutTasty  OMEGALAUGHING
//    HACKERMANS  HYPERCLAP2  BLAPBLAP  BLAAP  HYPEROMEGAPOGGERSCRAZY  POGPLANT  robDab Omegalol  FeelsAyayaMan
//    PepeSpin  PEEPERS  HYPERROBDAB  RareMoon  peepoT  moon2BASS