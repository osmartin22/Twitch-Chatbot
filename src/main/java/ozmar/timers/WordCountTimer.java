package ozmar.timers;

import ozmar.Bot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class WordCountTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startTimer() {
        final Runnable beeper = () -> {
            if (Bot.currentWordCountMap) {
                Bot.currentWordCountMap = false;
                if (!Bot.wordCountMap1.isEmpty()) {
                    Bot.databaseHelper.updateOrInsertWordCount(Bot.wordCountMap1);
                    Bot.wordCountMap1.clear();
                }

            } else {
                Bot.currentWordCountMap = true;
                if (!Bot.wordCountMap2.isEmpty()) {
                    Bot.databaseHelper.updateOrInsertWordCount(Bot.wordCountMap2);
                    Bot.wordCountMap2.clear();
                }
            }
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 5, 5, TimeUnit.SECONDS);
    }

}
