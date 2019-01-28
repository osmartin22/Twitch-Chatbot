package ozmar;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.net.URL;

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

    public RequestChat(String channelName) {
        this.channelName = channelName;
    }


    public void queryChatList() {
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(new URL(url + channelName + "/chatters"));
            parseEntireJson(parser);

        } catch (IOException e) {
            System.out.println("Failed to query the chat list " + e.getMessage());
        }
    }

    private void parseEntireJson(JsonParser jsonParser) {
        try {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String name = jsonParser.getCurrentName();
                if ("_links".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        System.out.println("Links: " + jsonParser.getText());
                    }

                } else if ("chatter_count".equals(name)) {
                    jsonParser.nextToken();
                    System.out.println("Chatter count: " + jsonParser.getIntValue());

                } else if ("chatters".equals(name)) {
                    jsonParser.nextToken();
                    System.out.println("Chatters");

                } else if ("vips".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println("VIP: " + jsonParser.getText());
                    }

                } else if ("moderators".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println("Mods: " + jsonParser.getText());
                    }

                } else if ("staff".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println("Staff: " + jsonParser.getText());
                    }

                } else if ("admins".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println("Admin: " + jsonParser.getText());
                    }

                } else if ("global_mods".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println("Global Mod: " + jsonParser.getText());
                    }

                } else if ("viewers".equals(name)) {
                    jsonParser.nextToken();
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println("Viewer: " + jsonParser.getText());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to parse " + e.getMessage());
        }
    }
}
