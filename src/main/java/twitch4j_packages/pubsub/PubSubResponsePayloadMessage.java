package twitch4j_packages.pubsub;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import twitch4j_packages.common.util.TypeConvert;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubSubResponsePayloadMessage {

    private String type;

    @JsonIgnore
    private Map<String, Object> messageData;

    @JsonIgnore
    private Map<String, Object> messageDataTags;

    @SuppressWarnings("unchecked")
    @JsonProperty("data")
    private void unpackMessage(String message) {
        this.messageData = TypeConvert.jsonToObject(message, Map.class);
        this.messageDataTags = this.messageData.containsKey("tags") ? (Map<String, Object>) this.messageData.get("tags") : new HashMap<>();
    }
}
