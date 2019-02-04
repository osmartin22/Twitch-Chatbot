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


        // TODO: ALLOW NEG NUMBERS IN CALC


        // !SpiritAnimal    You are an eagle
        // !robDab      "Robdab type of quotes"
        // !rateme      10 come here billyReady


// PepePls  Clap  KKomrade  KKool  gachiBASS lulWut  Clap2  AYAYA  HYPERLUL  billyReady  pepoS  theCHAMP  POGGERS  monkaGIGA monkaSHAKE  TriKool  PepeHands  HYPERLULWUT  BAOMN  RapThis  ppHop  peepoWink  monkaHmm PepoCheer  EZFingerGuns  pepeD  ZOINKS  pepeBASS  pepeJAM  FeelsLateMan  HYPEROMEGAZOINKS  HYPERCLAP nutTasty  OMEGALAUGHING  HACKERMANS  HYPERCLAP2  BLAPBLAP(->)  (<-)BLAAP  HYPEROMEGAPOGGERSCRAZY  POGPLANT  robDab Omegalol  FeelsAyayaMan  PepeSpin  PEEPERS  HYPERROBDAB  RareMoon  peepoT  moon2BASS
    }
}
