package ozmar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ozmar.timers.ChatListTimer;
import ozmar.timers.RecentChatterTimer;
import ozmar.timers.WordCountTimer;

public class Launcher {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BotConfig.class);

        RecentChatterTimer chatterTimer = new RecentChatterTimer();
        chatterTimer.startTimer();

        Bot bot = context.getBean(Bot.class);
        bot.initialize();
        bot.registerFeatures();
        bot.start();

        ChatListTimer chatListTimer = context.getBean(ChatListTimer.class);
        WordCountTimer wordCountTimer = context.getBean(WordCountTimer.class);
        chatListTimer.startTimer();
        wordCountTimer.startTimer();
//        InvocationTargetException
//        HystrixRuntimeException
//        ContextedRuntimeException
        // FUSION COMMAND
    }
}

/*
 PepePls  Clap  KKomrade  KKool  gachiBASS lulWut  Clap2  AYAYA  HYPERLUL  billyReady  pepoS
 theCHAMP  monkaGIGA  monkaSHAKE TriKool  PepeHands  HYPERLULWUT  BAOMN  RapThis  ppHop  peepoWink
 monkaHmm  PepoCheer EZFingerGuns  pepeD  ZOINKS  pepeBASS  pepeJAM  FeelsLateMan  HYPEROMEGAZOINKS
 HYPERCLAP  nutTasty OMEGALAUGHING  HACKERMANS  HYPERCLAP2  BLAPBLAP->  <-BLAAP  HYPEROMEGAPOGGERSCRAZY
 robDab  Omegalol  FeelsAyayaMan PepeSpin  PEEPERS  HYPERROBDAB  RareMoon  peepoT  moon2BASS


 Poggers  PepePls  Clap  KKomrade  KKool gachiBASS  lulWut  Clap2  AYAYA  HYPERLUL  billyReady  pepoS  theCHAMP  monkaGIGA monkaSHAKE  TriKool  PepeHands  HYPERLULWUT  BAOMN  RapThis  ppHop  peepoWink  monkaHmm PepoCheer  EZFingerGuns  pepeD  ZOINKS  pepeBASS  pepeJAM  FeelsLateMan  HYPEROMEGAZOINKS  HYPERCLAP nutTasty  OMEGALAUGHING  HACKERMANS  HYPERCLAP2  BLAPBLAP  BLAAP  HYPEROMEGAPOGGERSCRAZY  robDab  Omegalol FeelsAyayaMan  PepeSpin  PEEPERS  HYPERROBDAB  RareMoon  peepoT  moon2BASS
 */

// peepoWink
//oO( pepeBASS
//)