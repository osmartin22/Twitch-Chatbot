package twitch4j_packages.helix.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Stream (LiveStream)
 */
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stream {

    // Stream ID.
    @NonNull
    private Long id;

    // ID of the user who is streaming.
    @NonNull
    private Long userId;

    // ID of the game being played on the stream.
    private Long gameId;

    // Array of community IDs.
    @NonNull
    private List<UUID> communityIds;

    // Stream type: "live" or "" (in case of error).
    @NonNull
    private String type;

    // Stream title.
    @NonNull
    private String title;

    // Number of viewers watching the stream at the time of the query.
    @NonNull
    private Integer viewerCount;

    // UTC timestamp on when the stream started
    @NonNull
    private Calendar startedAt;

    // Stream language.
    @NonNull
    private String language;

    // Thumbnail URL of the stream. All image URLs have variable width and height. You can replace {width} and {height} with any values to get that size image
    @NonNull
    private String thumbnailUrl;

    /**
     * Gets the stream uptime based on the start date.
     *
     * @return The stream uptime.
     */
    public Duration getUptime() {
        Duration uptime = Duration.between(startedAt.toInstant(), Calendar.getInstance().toInstant());
        return uptime;
    }

    /**
     * Gets the thumbnail url for specific dimensions
     *
     * @param width  thumbnail width
     * @param height thumbnail height
     * @return String
     */
    public String getThumbnailUrl(Integer width, Integer height) {
        return thumbnailUrl.replaceAll(Pattern.quote("{width}"), width.toString()).replaceAll(Pattern.quote("{height}"), height.toString());
    }
}
