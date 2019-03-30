package twitch4j_packages.kraken.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@Getter
public class TwitchKrakenError {

    /**
     * Error Type
     */
    private String error;

    /**
     * Error Code
     */
    private String status;

    /**
     * Error Message
     */
    private String message;

}
