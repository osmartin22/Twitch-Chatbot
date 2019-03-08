package ozmar.api_calls;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RequestChat {

    // tmi.twitch.tv/group/user/channel_name/chatters
    private static final String url = "https://tmi.twitch.tv/group/user/";

    private RequestChat() {

    }

    /**
     * Calls Twitch Api and returns a list of users in chat
     * Should only be called when a fresh chat list is required
     * <p>
     * NOTE: this endpoint is unofficial and not supported but is the only way to
     * get the list of users in chat
     *
     * @return List of user names
     */
    @Nonnull
    public static List<String> queryChatList(@Nonnull String channelName) {
        List<String> chatList = new ArrayList<>();
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(new URL(url + channelName + "/chatters"));
            chatList = parseChatUsers(parser);

        } catch (IOException e) {
            System.out.println("Failed to query the chat list " + e.getMessage());
        }

        return new ArrayList<>(chatList);
    }

    @Nonnull
    private static List<String> parseChatUsers(@Nonnull JsonParser jsonParser) {
        List<String> chatUserList = new ArrayList<>();

        try {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String name = jsonParser.getCurrentName();
                if ("_links".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        jsonParser.nextToken();
                    }
                } else if ("chatter_count".equals(name)) {
                    jsonParser.nextToken();

                } else if ("chatters".equals(name)) {
                    jsonParser.nextToken();

                } else if ("vips".equals(name)) {
                    parserHelper(jsonParser, chatUserList);

                } else if ("moderators".equals(name)) {
                    parserHelper(jsonParser, chatUserList);

                } else if ("staff".equals(name)) {
                    parserHelper(jsonParser, chatUserList);

                } else if ("admins".equals(name)) {
                    parserHelper(jsonParser, chatUserList);

                } else if ("global_mods".equals(name)) {
                    parserHelper(jsonParser, chatUserList);

                } else if ("viewers".equals(name)) {
                    parserHelper(jsonParser, chatUserList);
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to parse " + e.getMessage());
        }

        return chatUserList;
    }

    private static void parserHelper(@Nonnull JsonParser jsonParser, @Nonnull List<String> list) {
        try {
            jsonParser.nextToken();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                list.add(jsonParser.getText());
            }
        } catch (IOException e) {
            System.out.println("Failed to parse " + e.getMessage());
        }
    }
}
