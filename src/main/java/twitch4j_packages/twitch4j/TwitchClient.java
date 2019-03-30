package twitch4j_packages.twitch4j;

import com.github.philippheuer.events4j.EventManager;
import twitch4j_packages.chat.TwitchChat;
import twitch4j_packages.common.annotation.Unofficial;
import twitch4j_packages.helix.TwitchHelix;
import twitch4j_packages.kraken.TwitchKraken;
import twitch4j_packages.pubsub.TwitchPubSub;
import twitch4j_packages.tmi.TwitchMessagingInterface;
import twitch4j_packages.twitch4j.modules.ModuleLoader;

public class TwitchClient {

    /**
     * Event Manager
     */
    private final EventManager eventManager;

    /**
     * API: Helix
     */
    private final TwitchHelix helix;

    /**
     * API: Kraken
     */
    private final TwitchKraken kraken;

    /**
     * API: TMI
     */
    private final TwitchMessagingInterface messagingInterface;

    /**
     * Chat
     */
    private final TwitchChat chat;

    /**
     * PubSub
     */
    private final TwitchPubSub pubsub;

    /**
     * Modules
     */
    private final ModuleLoader moduleLoader;

    /**
     * TwitchClientHelper
     * <p>
     * A helper method that contains some common use-cases, like follow events / go live event listeners / ...
     */
    private final TwitchClientHelper twitchClientHelper = new TwitchClientHelper(this);

    /**
     * Constructor
     *
     * @param eventManager       EventManager
     * @param helix              TwitchHelix
     * @param kraken             TwitchKraken
     * @param messagingInterface TwitchMessagingInterface
     * @param chat               TwitchChat
     * @param pubsub             TwitchPubSub
     */
    public TwitchClient(EventManager eventManager, TwitchHelix helix, TwitchKraken kraken, TwitchMessagingInterface messagingInterface, TwitchChat chat, TwitchPubSub pubsub) {
        this.eventManager = eventManager;
        this.helix = helix;
        this.kraken = kraken;
        this.messagingInterface = messagingInterface;
        this.chat = chat;
        this.pubsub = pubsub;

        // module loader
        this.moduleLoader = new ModuleLoader(this);

        // register with serviceMediator
        this.eventManager.getServiceMediator().addService("twitch4j", this);
    }

    /**
     * Get the event manager
     *
     * @return EventManager
     */
    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Get Helix
     *
     * @return TwitchHelix
     */
    public TwitchHelix getHelix() {
        if (this.helix == null) {
            throw new RuntimeException("You have not enabled the Helix Module! Please check out the documentation on Twitch4J -> Helix.");
        }

        return this.helix;
    }

    /**
     * Get Kraken
     *
     * @return TwitchKraken
     */
    @Deprecated
    public TwitchKraken getKraken() {
        if (this.kraken == null) {
            throw new RuntimeException("You have not enabled the Kraken Module! Please check out the documentation on Twitch4J -> Kraken.");
        }

        return this.kraken;
    }

    /**
     * Get MessagingInterface (API)
     *
     * @return TwitchMessagingInterface
     */
    @Unofficial
    public TwitchMessagingInterface getMessagingInterface() {
        if (this.messagingInterface == null) {
            throw new RuntimeException("You have not enabled the Twitch Messaging Interface Module! Please check out the documentation on Twitch4J -> TMI.");
        }

        return this.messagingInterface;
    }

    /**
     * Get Chat
     *
     * @return TwitchChat
     */
    public TwitchChat getChat() {
        if (this.chat == null) {
            throw new RuntimeException("You have not enabled the Chat Module! Please check out the documentation on Twitch4J -> Chat.");
        }

        return this.chat;
    }

    /**
     * Get PubSub
     *
     * @return TwitchPubSub
     */
    public TwitchPubSub getPubSub() {
        if (this.pubsub == null) {
            throw new RuntimeException("You have not enabled the PubSub Module! Please check out the documentation on Twitch4J -> PubSub.");
        }

        return this.pubsub;
    }

    /**
     * Get Module Loader
     *
     * @return ModuleLoader
     */
    public ModuleLoader getModuleLoader() {
        return this.moduleLoader;
    }

    /**
     * Get TwitchClientHelper
     *
     * @return TwitchClientHelper
     */
    public TwitchClientHelper getClientHelper() {
        return this.twitchClientHelper;
    }
}
