package ozmar;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import ozmar.database.DatabaseHandler;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// Launch at start of app
// Store usernames into database IF NOT EXISTS USER
// (MAYBE) if user in database not in received list, delete from database
// Can maybe have a counter that allows them to exist for X queries before deleting
// Increment counter if not in list, set to 0 if they appear in future queries


// Or maybe have a flag signifying in chat or not so there data isn't wiped entirely
// i.e. implement point system, want to keep user points even if they leave chat
// i.e. user only watches night stream,


// While users chat, store their ids in the database and increment counter for chat messages
// Save if sub or not by looking at permissions

public class RequestChat {

    // tmi.twitch.tv/group/user/channel_name/chatters
    private static final String url = "https://tmi.twitch.tv/group/user/";

    private String channelName;
    private final DatabaseHandler db;

    public RequestChat(@Nonnull String channelName) {
        this.channelName = channelName;
        this.db = new DatabaseHandler();
    }


    public List<String> queryChatList() {
        List<String> chatUserList = null;

        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(new URL(url + channelName + "/chatters"));
            chatUserList = parseChatUsers(parser);
            db.insertUserNames(chatUserList);

        } catch (IOException e) {
            System.out.println("Failed to query the chat list " + e.getMessage());
        }

        return chatUserList;
    }


    private List<String> parseChatUsers(JsonParser jsonParser) {

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

    private void parserHelper(@Nonnull JsonParser jsonParser, @Nonnull List<String> list) {
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
