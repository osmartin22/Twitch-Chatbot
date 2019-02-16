package ozmar.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ozmar.database.interfaces.DatabaseHandlerInterface;
import ozmar.features.*;

@Configuration
@ComponentScan("ozmar")
public class BotConfig {

    @Bean
    public Bot botBean(DatabaseHandlerInterface db, ChannelNotificationOnCheer channelNotificationOnCheer,
                       ChannelNotificationOnDonation channelNotificationOnDonation,
                       ChannelNotificationOnFollow channelNotificationOnFollow,
                       ChannelNotificationOnGiftSubscription channelNotificationOnGiftSubscription,
                       ChannelNotificationOnSubscription channelNotificationOnSubscription,
                       OnChatChannelMessage onChatChannelMessage, OnCommandReceived onCommandReceived) {

        return new Bot(db, channelNotificationOnCheer, channelNotificationOnDonation,
                channelNotificationOnFollow, channelNotificationOnGiftSubscription, channelNotificationOnSubscription,
                onChatChannelMessage, onCommandReceived);
    }

}
