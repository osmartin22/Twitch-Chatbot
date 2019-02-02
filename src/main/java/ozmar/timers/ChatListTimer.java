package ozmar.timers;

import com.github.twitch4j.helix.domain.UserList;
import org.apache.commons.collections4.ListUtils;
import ozmar.Bot;
import ozmar.RequestChat;
import ozmar.database.DatabaseHandler;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ChatListTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final DatabaseHandler db;

    public ChatListTimer() {
        db = new DatabaseHandler();
    }

    public void startTimer() {
        final Runnable beeper = () -> {
            RequestChat requestChat = new RequestChat("dreamleague");
            List<String> userNameList = requestChat.queryChatList();
            List<List<String>> partition = ListUtils.partition(userNameList, 100);

            for (List<String> list : partition) {
                UserList userList;
                try {
                    userList = Bot.helixCommands.getUsersList(null, list);

                    // Try to get the data once more else remove the list and try again the next time
                    // Temp solution since api prevents extending the Hystrix default timeout
                    // To solve this I would have to create my own Helix interface and set a timeout
                    // Response is OK 200 but Hystrix timeouts before some responses occur
                } catch (Throwable e) {
                    System.out.println("Timed out, trying again " + e.getMessage());
                    userList = Bot.helixCommands.getUsersList(null, list);
                }

                if (userList != null) {
                    db.addChatDataToTable(userList.getUsers());
                }
            }
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 0, 1, TimeUnit.MINUTES);
    }
}
