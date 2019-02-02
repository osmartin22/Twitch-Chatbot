package ozmar.timers;

import ozmar.ChatUser;
import ozmar.buffer.ChatDataBuffer;
import ozmar.buffer.WordCountBuffer;
import ozmar.database.DatabaseHandler;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WordCountTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final DatabaseHandler db;
    private final WordCountBuffer wordCountBuffer;
    private final ChatDataBuffer chatDataBuffer;

    public WordCountTimer() {
        this.db = new DatabaseHandler();
        this.wordCountBuffer = new WordCountBuffer();
        this.chatDataBuffer = new ChatDataBuffer();
    }

    public void startTimer() {
        final Runnable beeper = () -> {

            Map<String, Integer> wordCountMap = wordCountBuffer.getMap();
            db.updateOrInsertWordCount(wordCountMap);
            wordCountBuffer.clearMap();

            Map<Long, ChatUser> chatUserMap = chatDataBuffer.getMap();
            db.updatePoints(chatUserMap);
            chatDataBuffer.clearMap();
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 5, 3, TimeUnit.SECONDS);
    }

}
