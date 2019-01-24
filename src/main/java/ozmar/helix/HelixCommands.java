package ozmar.helix;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;

import java.util.List;
import java.util.UUID;

public class HelixCommands {

    //TODO: MOVE THIS TO ANOTHER FILE TO KEEP STATIC METHODS ONLY
    private static TwitchClient twitchClient;

    private HelixCommands() {

    }

    public static void setTwitchClient(TwitchClient client) {
        twitchClient = client;
    }

    /**
     * Returns information about the requested users
     *
     * @param oAuthToken token authorization
     * @param userIds    user Id
     * @param userNames  user login name
     * @return UserLIst
     */
    public static UserList getUsersList(String oAuthToken, List<String> userIds, List<String> userNames) {
        return twitchClient.getHelix().getUsers(oAuthToken, userIds, userNames).execute();
    }


    /**
     * @param after       Cursor for forward pagination
     * @param before      Cursor for backward pagination
     * @param communityId Returns streams in a specified community ID. You can specify up to 100 IDs
     * @param first       Maximum number of objects to return. Maximum: 100. Default: 20
     * @param gameIds     Streams broadcasting a specified game ID. You can specify up to 100 IDs
     * @param language    Stream language
     * @param userIds     Streams broadcast by one or more specified user IDs. You can specify up to 100 IDs.
     * @param userLogin   Streams broadcast by one or more specified user login names. You can specify up to 100 names.
     * @return StreamList
     */
    public static StreamList getStreams(String after, String before, List<UUID> communityId,
                                        Integer first, List<String> gameIds, String language,
                                        List<String> userIds, List<String> userLogin) {

        return twitchClient.getHelix().getStreams(after, before, first, communityId, gameIds,
                language, userIds, userLogin).execute();

    }

    public static FollowList getFollowers(String fromId, String toId, String after, Integer limit) {
        return twitchClient.getHelix().getFollowers(fromId, toId, after, limit).execute();
    }
}
