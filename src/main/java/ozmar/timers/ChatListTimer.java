package ozmar.timers;

import com.github.twitch4j.helix.domain.UserList;
import org.apache.commons.collections4.ListUtils;
import ozmar.api_calls.RequestChat;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.setup.Bot;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ChatListTimer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final DatabaseHandlerInterface db;

    public ChatListTimer(DatabaseHandlerInterface db) {
        this.db = db;
    }

    public void startTimer() {
        final Runnable beeper = () -> {
            List<String> userNameList = RequestChat.queryChatList("moonmoon_ow");
            List<List<String>> partition = ListUtils.partition(userNameList, 100);

            // Remove names from the list if they exist in the database
            db.getChatDao().checkIfNamesExist(userNameList);
            System.out.print("Fetching " + userNameList.size() + " users in " + partition.size() + " partitions: ");

            for (List<String> list : partition) {
                UserList userList;
                try {
                    userList = Bot.helixCommands.getUsersList(null, list);
                } catch (Exception e) {
                    /* TODO: From testing, the api call doesn't fail, it's just that the call
                        sometimes takes longer than the timeout and is registered as a timeout exception
                        but the result is received a little bit later
                     */
                    System.out.println("Getting user info timed out" + e.getMessage());
                    continue;   // Skip list, will be called the next time the timer occurs
                }

                if (userList != null && !userList.getUsers().isEmpty()) {
                    System.out.print("Storing " + userList.getUsers().size() + " users ");
                    db.getChatDao().addUserList(userList.getUsers());
                }
            }

            System.out.println();
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 0, 60, TimeUnit.SECONDS);
    }
}
