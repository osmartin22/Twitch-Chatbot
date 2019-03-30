package ozmar.commands;

import ozmar.commands.interfaces.TwitchCallCommandInterface;
import ozmar.database.tables.interfaces.DatabaseHandlerInterface;
import ozmar.setup.Bot;
import ozmar.utils.StringHelper;
import ozmar.utils.TimeHelper;
import twitch4j_packages.chat.events.CommandEvent;
import twitch4j_packages.helix.domain.FollowList;
import twitch4j_packages.helix.domain.StreamList;
import twitch4j_packages.helix.domain.UserList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TwitchCallCommand implements TwitchCallCommandInterface {

    private final DatabaseHandlerInterface db;

    public TwitchCallCommand(DatabaseHandlerInterface db) {
        this.db = db;
    }

    @Nonnull
    @Override
    public String uptime(@Nonnull CommandEvent event) {
        String channelName = event.getSourceId();
        UserList userList = Bot.helixCommands.getUsersList(null, Collections.singletonList(channelName));
        if (userList == null) {
            return somethingWentWrong(event.getUser().getName());
        }

        Long channelId = userList.getUsers().get(0).getId();
        StreamList streamList = Bot.helixCommands.getStreams(null, null, null, null,
                null, null, Collections.singletonList(channelId), null);
        if (streamList == null) {
            return somethingWentWrong(event.getUser().getName());
        }

        if (!streamList.getStreams().isEmpty()) {
            Calendar startTime = streamList.getStreams().get(0).getStartedAt();
            Calendar currentTime = Calendar.getInstance();
            return String.format("%s has been live for %s", channelName, TimeHelper.getTimeDiff(startTime, currentTime));
        }
        return String.format("%s is currently offline", channelName);
    }

    @Nonnull
    @Override
    public String followage(@Nonnull CommandEvent event) {
        List<String> usersInfoList = new ArrayList<>();
        String channelName = event.getSourceId();
        long channelId = db.getChatDao().getUserId(channelName);
        if (channelId == -1) {
            usersInfoList.add(channelName);
        }

        String name = event.getCommand().trim();
        String userToCheckName;
        if (name.isEmpty()) {
            userToCheckName = event.getUser().getName();
        } else {
            userToCheckName = StringHelper.getFirstWord(name);
        }

        long userToCheckId = (event.getCommand().trim().isEmpty()) ?
                event.getUser().getId() : db.getChatDao().getUserId(userToCheckName);
        if (userToCheckId == -1) {
            usersInfoList.add(userToCheckName);
        }

        UserList userList = (!usersInfoList.isEmpty()) ? Bot.helixCommands.getUsersList(null, usersInfoList) : null;
        if (userList != null && !userList.getUsers().isEmpty()) {
            if (channelId == -1) {
                channelId = userList.getUsers().get(0).getId();
                if (userToCheckId == -1) {
                    userToCheckId = userList.getUsers().get(1).getId();
                }
            } else {
                userToCheckId = userList.getUsers().get(0).getId();
            }
        }

        // Not a real user name
        if (userToCheckId == -1) {
            return String.format("%s, %s does not exist", event.getUser().getName(), userToCheckName);
        }

        FollowList followList = Bot.helixCommands.getFollowers(userToCheckId, channelId, null, 1);
        if (followList == null) {
            return String.format("%s, %s does not exist", event.getUser().getName(), userToCheckName);
        }

        if (!followList.getFollows().isEmpty()) {
            return String.format("%s has been following %s since %s",
                    userToCheckName, channelName, followList.getFollows().get(0).getFollowedAt());
        }

        return String.format("%s is not following %s", userToCheckName, channelName);
    }


    @Nonnull
    private String somethingWentWrong(@Nonnull String userName) {
        return String.format("%s, something went wrong :(", userName);
    }
}
