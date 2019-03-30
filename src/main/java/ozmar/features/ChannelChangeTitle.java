package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import twitch4j_packages.common.events.channel.ChannelChangeTitleEvent;

@Slf4j
public class ChannelChangeTitle {
    @EventSubscriber
    public void onTitleChange(ChannelChangeTitleEvent event) {
//        System.out.println("Title change");
//        log.info();
    }
}
