package ozmar.timers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import ozmar.api_calls.RequestChat;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.setup.Bot;
import twitch4j_packages.helix.domain.UserList;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
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
            int originalChatterCount = userNameList.size();
            db.getChatDao().checkIfNamesExist(userNameList);

            int usersSavedCount = 0;
            for (List<String> list : partition) {
                UserList userList;
                try {
                    userList = Bot.helixCommands.getUsersList(null, list);
                } catch (Exception e) {
                    log.error("Getting user info timed out: {}", e.getMessage());
                    continue;   // Skip list, will be called the next time the timer occurs
                }

                if (userList != null && !userList.getUsers().isEmpty()) {
                    usersSavedCount += userList.getUsers().size();
                    db.getChatDao().addUserList(userList.getUsers());
                }
            }

            log.info("ChatterCount: {}, Saved {}/{} users from {} partitions",
                    originalChatterCount, usersSavedCount, userNameList.size(), partition.size());
        };

        final ScheduledFuture<?> fixedRateTimer =
                scheduler.scheduleAtFixedRate(beeper, 0, 60, TimeUnit.SECONDS);
    }
}
