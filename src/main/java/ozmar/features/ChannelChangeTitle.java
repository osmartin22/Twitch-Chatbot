package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import com.github.twitch4j.common.events.channel.ChannelChangeTitleEvent;

public class ChannelChangeTitle {
    @EventSubscriber
    public void onTitleChange(ChannelChangeTitleEvent event) {
        System.out.println("Title change");

    }
}
