package ozmar.pokemonBattle;

import twitch4j_packages.chat.TwitchChat;

import javax.annotation.Nonnull;
import java.util.Map;

public class PokeBattleView implements PokeBattleViewInterface {

    private TwitchChat twitchChat;
    private String channelName;

    // TODO: Private messages through Twitch Chat are sent by username and not by user id
    //  If a user changes their username during a battle, messages will no longer be able to be sent
    //  Need to save user id and username and be able to update the username if it changes
    private Map<Long, String> userMap;

    public PokeBattleView(@Nonnull TwitchChat twitchChat, @Nonnull String channelName, @Nonnull Map<Long, String> map) {
        this.twitchChat = twitchChat;
        this.channelName = channelName;
        this.userMap = map;
    }

    @Override
    public void sendUserMessage(long userId, @Nonnull String message) {
//        twitchChat.sendPrivateMessage(userMap.get(userId), message);
        System.out.println(String.format("User message: %s - %s", userId, message));
    }

    @Override
    public void sendMessageForAll(@Nonnull String message) {
//        twitchChat.sendMessage(channelName, message);
        System.out.println("Normal Message: " + message);
    }
}
