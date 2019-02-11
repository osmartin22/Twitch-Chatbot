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

    public ChatListTimer(DatabaseHandler db) {
        this.db = db;
    }

    public void startTimer() {
        final Runnable beeper = () -> {
            RequestChat requestChat = new RequestChat("moonmoon_ow");
            List<String> userNameList = requestChat.queryChatList();
            List<List<String>> partition = ListUtils.partition(userNameList, 100);

            // Remove names from the list if they exist in the database
            db.checkIfNamesExist(userNameList);
            System.out.println("Fetching " + userNameList.size() + " users in " + partition.size() + " partitions");

            for (List<String> list : partition) {
                UserList userList;
                try {
                    userList = Bot.helixCommands.getUsersList(null, list);
                } catch (Throwable e) {
                    System.out.println("Timed out, trying again " + e.getMessage());
                    continue;   // Skip list, will be called the next time the timer occurs
                }

                if (userList != null && !userList.getUsers().isEmpty()) {
                    System.out.println("Storing " + userList.getUsers().size() + " users");
                    db.addUserList(userList.getUsers());
                }
            }
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 0, 60, TimeUnit.SECONDS);
    }
}
