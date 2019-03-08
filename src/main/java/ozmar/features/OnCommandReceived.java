package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import javafx.util.Pair;
import ozmar.commands.interfaces.HandleCommandInterface;

import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OnCommandReceived {

    private final HandleCommandInterface handleCommand;

    private final long OUTPUT_COOLDOWN = 1000;
    private long lastSentMessage = 0;
    private final Deque<Pair<CommandEvent, String>> outputQueue;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public OnCommandReceived(HandleCommandInterface handleCommand, Deque<Pair<CommandEvent, String>> commandOutputQueue) {
        this.handleCommand = handleCommand;
        this.outputQueue = commandOutputQueue;
        startTimer();
    }

    @EventSubscriber
    public void onCommand(CommandEvent event) {
        System.out.println(event);
        handleCommand.setCommandEvent(event);
        String output = handleCommand.decideCommand();
        System.out.println();

        if (output != null) {
            long currTime = System.currentTimeMillis();
            if (currTime - lastSentMessage > OUTPUT_COOLDOWN && outputQueue.isEmpty()) {
                event.respondToUser(output);
                lastSentMessage = currTime;
            } else {
                outputQueue.add(new Pair<>(event, output));
            }
        }
    }

    public void startTimer() {
        final Runnable sendQueuedOutput = () -> {
            long currTime = System.currentTimeMillis();
            if (currTime - lastSentMessage > OUTPUT_COOLDOWN && !outputQueue.isEmpty()) {
                Pair<CommandEvent, String> pair = outputQueue.getFirst();
                pair.getKey().respondToUser(pair.getValue());
                outputQueue.removeFirst();
                lastSentMessage = currTime;
            }
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(sendQueuedOutput, 1000, 500, TimeUnit.MILLISECONDS);
    }
}
