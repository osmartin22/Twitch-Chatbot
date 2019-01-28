package ozmar.timers;

import ozmar.RequestChat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ChatListTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startTimer() {
        final Runnable beeper = () -> {
            RequestChat requestChat = new RequestChat("moonmoon_ow");
            requestChat.queryChatList();

            // queryChatList should return a list
            // store the list into the database

        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 0, 1, TimeUnit.MINUTES);
    }
}
