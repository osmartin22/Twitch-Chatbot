package ozmar.commands;

import org.springframework.context.MessageSource;
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
import java.util.*;

public class TwitchCallCommand implements TwitchCallCommandInterface {

    private final DatabaseHandlerInterface db;
    private final MessageSource source;
    private final Locale defaultLocale;

    public TwitchCallCommand(MessageSource messageSource, DatabaseHandlerInterface db) {
        this.source = messageSource;
        this.db = db;
        this.defaultLocale = new Locale("en");
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

            return source.getMessage("cmd.live.status.on",
                    new String[]{channelName, TimeHelper.getTimeDiff(startTime, currentTime)}, defaultLocale);
        }

        return source.getMessage("cmd.live.status.off", new String[]{channelName}, defaultLocale);
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
            return source.getMessage("cmd.followage.dne",
                    new String[]{event.getUser().getName(), userToCheckName}, defaultLocale);
        }

        FollowList followList = Bot.helixCommands.getFollowers(userToCheckId, channelId, null, 1);
        if (followList == null) {
            return source.getMessage("cmd.followage.dne",
                    new String[]{event.getUser().getName(), userToCheckName}, defaultLocale);
        }

        if (!followList.getFollows().isEmpty()) {
            return source.getMessage("cmd.followage.since",
                    new String[]{userToCheckName, channelName, followList.getFollows().get(0).getFollowedAt().toString()}, defaultLocale);
        }

        return source.getMessage("cmd.followage.not", new String[]{userToCheckName, channelName}, defaultLocale);
    }


    @Nonnull
    private String somethingWentWrong(@Nonnull String userName) {
        return source.getMessage("cmd.error", new String[]{userName}, defaultLocale);
    }
}
