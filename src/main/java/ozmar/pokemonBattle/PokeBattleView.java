package ozmar.pokemonBattle;

import twitch4j_packages.chat.TwitchChat;

import javax.annotation.Nonnull;

public class PokeBattleView implements PokeBattleViewInterface {

    private TwitchChat twitchChat;
    private String channelName;

    public PokeBattleView(@Nonnull TwitchChat twitchChat, @Nonnull String channelName) {
        this.twitchChat = twitchChat;
        this.channelName = channelName;
    }

    public PokeBattleView() {

    }

    @Override
    public void sendUserMessage(@Nonnull String message) {
//        twitchChat.sendUserMessage();
        System.out.println("User Message: " + message);
    }

    @Override
    public void sendMessageForAll(@Nonnull String message) {
//        twitchChat.sendMessageForAll();
        System.out.println("Normal Message: " + message);
    }
}
