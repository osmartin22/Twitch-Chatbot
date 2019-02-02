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
            RequestChat requestChat = new RequestChat("moonmoon_ow");
            List<String> userNameList = requestChat.queryChatList();
            List<List<String>> partition = ListUtils.partition(userNameList, 100);

            // Remove names from the list if they exist in the database
            db.checkIfNamesExist(userNameList);

            for (List<String> list : partition) {
                UserList userList;
                try {
                    userList = Bot.helixCommands.getUsersList(null, list);

                    // Try to get the data once more else remove the list and try again the next time
                    // we query the chat list
                    // Temp solution since it would require rewriting parts of the api I am using
                    // to extend the timeout
                } catch (Throwable e) {
                    System.out.println("Timed out, trying again " + e.getMessage());
                    userList = Bot.helixCommands.getUsersList(null, list);
                }

                if (userList != null) {
                    db.addUserList(userList.getUsers());
                }
            }
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 0, 1, TimeUnit.MINUTES);
    }
}
