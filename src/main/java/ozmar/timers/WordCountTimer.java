package ozmar.timers;

import ozmar.buffer.interfaces.ChatDataBufferInterface;
import ozmar.buffer.interfaces.WordCountBufferInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.user.ChatUser;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WordCountTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final DatabaseHandlerInterface db;
    private final WordCountBufferInterface wordCountBuffer;
    private final ChatDataBufferInterface chatDataBuffer;

    public WordCountTimer(DatabaseHandlerInterface db, WordCountBufferInterface wordCountBuffer,
                          ChatDataBufferInterface chatDataBuffer) {
        this.db = db;
        this.wordCountBuffer = wordCountBuffer;
        this.chatDataBuffer = chatDataBuffer;
    }

    public void startTimer() {
        final Runnable beeper = () -> {
            Map<String, Integer> wordCountMap = wordCountBuffer.getMap();
            db.getWordCountDao().updateOrInsertWordCount(wordCountMap);
            wordCountBuffer.clearMap();

            Map<Long, ChatUser> chatUserMap = chatDataBuffer.getMap();
            db.getChatDao().updatePoints(chatUserMap);
            chatDataBuffer.clearMap();
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 5, 3, TimeUnit.SECONDS);
    }
}
