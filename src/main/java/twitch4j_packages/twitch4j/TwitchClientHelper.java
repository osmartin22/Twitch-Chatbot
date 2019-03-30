package twitch4j_packages.twitch4j;

import com.github.philippheuer.events4j.domain.Event;
import lombok.extern.slf4j.Slf4j;
import twitch4j_packages.chat.events.channel.FollowEvent;
import twitch4j_packages.common.events.channel.ChannelChangeGameEvent;
import twitch4j_packages.common.events.channel.ChannelChangeTitleEvent;
import twitch4j_packages.common.events.channel.ChannelGoLiveEvent;
import twitch4j_packages.common.events.channel.ChannelGoOfflineEvent;
import twitch4j_packages.common.events.domain.EventChannel;
import twitch4j_packages.common.events.domain.EventUser;
import twitch4j_packages.helix.domain.*;
import twitch4j_packages.twitch4j.domain.ChannelCache;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * A helper class that covers a few basic use cases of most library users
 */
@Slf4j
public class TwitchClientHelper {

    /**
     * Holds the channels that are checked for live/offline state changes
     */
    private List<EventChannel> listenForGoLive = new ArrayList<>();

    /**
     * Holds the channels that are checked for new followers
     */
    private List<EventChannel> listenForFollow = new ArrayList<>();

    /**
     * TwitchClient
     */
    private TwitchClient twitchClient;

    /**
     * Event Listener Thread
     */
    protected final Thread eventGenerationThread;

    /**
     * Channel Information Cache
     */
    private Map<Long, ChannelCache> channelInformation = new HashMap<>();

    /**
     * Constructor
     *
     * @param twitchClient TwitchClient
     */
    public TwitchClientHelper(TwitchClient twitchClient) {
        this.twitchClient = twitchClient;

        // Thread
        this.eventGenerationThread = new Thread(() -> {
            log.debug("Started TwitchClientHelper Thread to listen for goLive/Follow events");

            while (true) {
                try {
                    // check if the thread was interrupted
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }

                    // check go live / stream events
                    try {
                        listenForGoLive.forEach(channel -> {
                            ChannelCache currentChannelCache = channelInformation.get(channel.getId());
                            StreamList streams = twitchClient.getHelix().getStreams(null, null, 1, null, null, null, Arrays.asList(channel.getId()), null).execute();

                            boolean dispatchGoLiveEvent = false;
                            boolean dispatchGoOfflineEvent = false;
                            boolean dispatchTitleChangedEvent = false;
                            boolean dispatchGameChangedEvent = false;

                            if (streams.getStreams().size() > 0) {
                                // is live
                                Stream stream = streams.getStreams().get(0);

                                // - live status
                                if (currentChannelCache.getIsLive() != null && currentChannelCache.getIsLive() == false) {
                                    dispatchGoLiveEvent = true;
                                }
                                currentChannelCache.setIsLive(true);
                                boolean wasAlreadyLive = dispatchGoLiveEvent != true && currentChannelCache.getIsLive() == true;

                                // - change stream title event
                                if (wasAlreadyLive && currentChannelCache.getTitle() != null && !currentChannelCache.getTitle().equalsIgnoreCase(stream.getTitle())) {
                                    dispatchTitleChangedEvent = true;
                                }
                                currentChannelCache.setTitle(stream.getTitle());

                                // - change game event
                                if (wasAlreadyLive && currentChannelCache.getGameId() != null && !currentChannelCache.getGameId().equals(stream.getGameId())) {
                                    dispatchGameChangedEvent = true;
                                }
                                currentChannelCache.setGameId(stream.getGameId());
                            } else {
                                // was online previously?
                                if (currentChannelCache.getIsLive() != null && currentChannelCache.getIsLive() == true) {
                                    dispatchGoOfflineEvent = true;
                                }

                                // is offline
                                currentChannelCache.setIsLive(false);
                                currentChannelCache.setTitle(null);
                                currentChannelCache.setGameId(null);
                            }

                            // dispatch events
                            // - go live event
                            if (dispatchGoLiveEvent) {
                                Event event = new ChannelGoLiveEvent(channel, currentChannelCache.getTitle(), currentChannelCache.getGameId());
                                twitchClient.getEventManager().dispatchEvent(event);
                            }
                            // - go offline event
                            if (dispatchGoOfflineEvent) {
                                Event event = new ChannelGoOfflineEvent(channel);
                                twitchClient.getEventManager().dispatchEvent(event);
                            }
                            // - title changed event
                            if (dispatchTitleChangedEvent) {
                                Event event = new ChannelChangeTitleEvent(channel, currentChannelCache.getTitle());
                                twitchClient.getEventManager().dispatchEvent(event);
                            }
                            // - game changed event
                            if (dispatchGameChangedEvent) {
                                Event event = new ChannelChangeGameEvent(channel, currentChannelCache.getGameId());
                                twitchClient.getEventManager().dispatchEvent(event);
                            }
                        });
                    } catch (Exception ex) {
                        log.error("Failed to check for Stream Events (Live/Offline/...): " + ex.getMessage());
                        ex.printStackTrace();
                    }

                    // check follow events
                    try {
                        for (EventChannel channel : listenForFollow) {
                            ChannelCache currentChannelCache = channelInformation.get(channel.getId());
                            LocalDateTime lastFollowDate = null;

                            if (currentChannelCache.getLastFollowCheck() != null) {
                                FollowList followList = twitchClient.getHelix().getFollowers(null, channel.getId(), null, null).execute();
                                for (Follow follow : followList.getFollows()) {
                                    // update lastFollowDate
                                    if (lastFollowDate == null || follow.getFollowedAt().compareTo(lastFollowDate) > 0) {
                                        lastFollowDate = follow.getFollowedAt();
                                    }

                                    // is new follower?
                                    if (follow.getFollowedAt().compareTo(currentChannelCache.getLastFollowCheck()) > 0) {
                                        // dispatch event
                                        FollowEvent event = new FollowEvent(channel, new EventUser(follow.getFromId(), follow.getFromName()));
                                        twitchClient.getEventManager().dispatchEvent(event);
                                    }
                                }
                            }

                            if (currentChannelCache.getLastFollowCheck() == null) {
                                // only happens if the user doesn't have any followers at all
                                currentChannelCache.setLastFollowCheck(LocalDateTime.now(ZoneId.of("UTC")));
                            } else {
                                // tracks the date of the latest follow to identify new ones later on
                                currentChannelCache.setLastFollowCheck(lastFollowDate);
                            }
                        }

                    } catch (Exception ex) {
                        log.error("Failed to check for Follow Events: " + ex.getMessage());
                        ex.printStackTrace();
                    }

                    // sleep one second
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    // exit thread, since it's not needed anymore
                    log.debug("TwitchClientHelper Thread has been disabled, it's not needed anymore since we aren't listening for any events with the helper.");
                    return;
                } catch (Exception ex) {
                    log.error("Failed to check for events in TwitchClientHelper Thread: " + ex.getMessage());
                }
            }
        });
    }

    /**
     * GoLive Listener
     *
     * @param channelName Channel Name
     */
    public void enableStreamEventListener(String channelName) {
        UserList users = twitchClient.getHelix().getUsers(null, null, Arrays.asList(channelName)).execute();

        if (users.getUsers().size() == 1) {
            users.getUsers().forEach(user -> {
                // add to list
                listenForGoLive.add(new EventChannel(user.getId(), user.getLogin()));

                // initialize cache
                if (!channelInformation.containsKey(user.getId())) {
                    channelInformation.put(user.getId(), new ChannelCache(null, null, null, null));
                }

                // start thread if needed
                startOrStopEventGenerationThread();
            });
        } else {
            log.error("Failed to add channel " + channelName + " to GoLive Listener, maybe it doesn't exist!");
        }
    }

    /**
     * Follow Listener
     *
     * @param channelName Channel Name
     */
    public void enableFollowEventListener(String channelName) {
        UserList users = twitchClient.getHelix().getUsers(null, null, Arrays.asList(channelName)).execute();

        if (users.getUsers().size() == 1) {
            users.getUsers().forEach(user -> {
                // add to list
                listenForFollow.add(new EventChannel(user.getId(), user.getLogin()));

                // initialize cache
                if (!channelInformation.containsKey(user.getId())) {
                    channelInformation.put(user.getId(), new ChannelCache(null, null, null, null));
                }

                // start thread if needed
                startOrStopEventGenerationThread();
            });
        } else {
            log.error("Failed to add channel " + channelName + " to Follow Listener, maybe it doesn't exist!");
        }
    }

    /**
     * Start or quit the thread, depending on usage
     */
    private void startOrStopEventGenerationThread() {
        if (listenForGoLive.size() > 0 || listenForFollow.size() > 0) {
            // thread should be active
            if (!eventGenerationThread.isAlive()) {
                eventGenerationThread.start();
            }
        } else {
            // thread can be stopped
            if (eventGenerationThread.isAlive() && !eventGenerationThread.isInterrupted()) {
                eventGenerationThread.interrupt();
            }
        }
    }

}
