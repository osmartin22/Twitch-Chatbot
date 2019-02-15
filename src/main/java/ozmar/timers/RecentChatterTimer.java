package ozmar.timers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RecentChatterTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static Map<String, Long> mostRecent = new HashMap<>();

    public void startTimer() {
        final Runnable beeper = () -> {
            int l = 120000;
            mostRecent.entrySet().removeIf(entry -> System.currentTimeMillis() - entry.getValue() > l);
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 2, 2, TimeUnit.MINUTES);
    }
}
