package ozmar.timers;

import ozmar.Bot;
import ozmar.database.DatabaseHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WordCountTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final DatabaseHandler db;

    public WordCountTimer() {
        this.db = new DatabaseHandler();
    }

    public void startTimer() {
        final Runnable beeper = () -> {
            if (Bot.currentWordCountMap) {
                Bot.currentWordCountMap = false;
                if (!Bot.wordCountMap1.isEmpty()) {
                    db.updateOrInsertWordCount(Bot.wordCountMap1);
                    Bot.wordCountMap1.clear();
                }

            } else {
                Bot.currentWordCountMap = true;
                if (!Bot.wordCountMap2.isEmpty()) {
                    db.updateOrInsertWordCount(Bot.wordCountMap2);
                    Bot.wordCountMap2.clear();
                }
            }


            if (Bot.currentChatDataMap) {
                Bot.currentChatDataMap = false;
                if (!Bot.chatData1.isEmpty()) {
                    db.updatePoints(Bot.chatData1);
                    Bot.chatData1.clear();
                }

            } else {
                Bot.currentChatDataMap = true;
                if (!Bot.chatData2.isEmpty()) {
                    db.updatePoints(Bot.chatData2);
                    Bot.chatData2.clear();
                }
            }
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 5, 5, TimeUnit.SECONDS);
    }

}
