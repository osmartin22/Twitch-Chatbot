package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import javafx.util.Pair;
import ozmar.commands.interfaces.HandleCommandInterface;
import twitch4j_packages.chat.events.CommandEvent;

import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OnCommandReceived {

    private final HandleCommandInterface handleCommand;

    private final long OUTPUT_COOLDOWN = 1500;
    private long lastSentMessage = 0;
    private final Deque<Pair<CommandEvent, String>> outputQueue;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> fixedRateTimer;

    public OnCommandReceived(HandleCommandInterface handleCommand, Deque<Pair<CommandEvent, String>> commandOutputQueue) {
        this.handleCommand = handleCommand;
        this.outputQueue = commandOutputQueue;
    }

    @EventSubscriber
    public void onCommand(CommandEvent event) {
        handleCommand.setCommandEvent(event);
        String output = handleCommand.decideCommand();

        if (output != null) {
            System.out.println(event);
            System.out.println();
            long currTime = System.currentTimeMillis();
            if (currTime - lastSentMessage > OUTPUT_COOLDOWN && outputQueue.isEmpty()) {
                if (fixedRateTimer != null) {
                    fixedRateTimer.cancel(true);
                    fixedRateTimer = null;
                }
                event.respondToUser(output);
                lastSentMessage = currTime;
            } else {
                if (fixedRateTimer == null) {
                    fixedRateTimer = startTimer();
                }
                outputQueue.add(new Pair<>(event, output));
            }
        }
    }

    public ScheduledFuture<?> startTimer() {
        final Runnable sendQueuedOutput = () -> {
            long currTime = System.currentTimeMillis();
            if (currTime - lastSentMessage > OUTPUT_COOLDOWN && !outputQueue.isEmpty()) {
                Pair<CommandEvent, String> pair = outputQueue.getFirst();
                pair.getKey().respondToUser(pair.getValue());
                outputQueue.removeFirst();
                lastSentMessage = currTime;
            }

            if (outputQueue.isEmpty()) {
                fixedRateTimer.cancel(true);
                fixedRateTimer = null;
            }
        };

        return scheduler.scheduleAtFixedRate(sendQueuedOutput, 1000, 750, TimeUnit.MILLISECONDS);
    }
}
