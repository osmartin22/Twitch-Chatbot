package ozmar.timers;

import ozmar.buffer.interfaces.RecentChattersInterface;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RecentChatterTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final RecentChattersInterface recentChatters;

    public RecentChatterTimer(RecentChattersInterface recentChatters) {
        this.recentChatters = recentChatters;
    }

    public void startTimer() {
        final Runnable beeper = () -> {
            int l = 240000;
            recentChatters.getRecentChatters().entrySet()
                    .removeIf(entry -> System.currentTimeMillis() - entry.getValue() > l);
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 2, 2, TimeUnit.MINUTES);
    }
}
