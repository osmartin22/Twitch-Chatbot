package ozmar.helix;

import com.github.twitch4j.helix.TwitchHelix;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.StreamList;
import com.github.twitch4j.helix.domain.UserList;

import java.util.List;
import java.util.UUID;

public class HelixCommands {

    private final TwitchHelix twitchHelix;
    private final String oAuthToken;


    public HelixCommands(TwitchHelix twitchHelix, String oAuthToken) {
        this.twitchHelix = twitchHelix;
        this.oAuthToken = oAuthToken;
    }

    /**
     * Returns information about the requested users
     *
     * @param userIds   user Id
     * @param userNames user login name
     * @return UserLIst
     */
    public UserList getUsersList(List<Long> userIds, List<String> userNames) {

        return twitchHelix.getUsers(oAuthToken, userIds, userNames).execute();
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
    public StreamList getStreams(String after, String before, List<UUID> communityId,
                                 Integer first, List<String> gameIds, String language,
                                 List<Long> userIds, List<String> userLogin) {

        return twitchHelix.getStreams(after, before, first, communityId, gameIds,
                language, userIds, userLogin).execute();

    }

    public FollowList getFollowers(Long fromId, Long toId, String after, Integer limit) {
        return twitchHelix.getFollowers(fromId, toId, after, limit).execute();
    }
}
