package twitch4j_packages.twitch4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Channel Cache
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ChannelCache {

    /**
     * IsLive
     */
    private Boolean isLive;

    /**
     * Stream Title
     */
    private String title;

    /**
     * Current Game Id
     */
    private Long gameId;

    /**
     * Last Follow Check
     */
    private LocalDateTime lastFollowCheck;
}
