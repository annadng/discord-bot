package com.annadang;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import com.google.api.services.youtube.YouTube;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DiscordEventListener extends ListenerAdapter {

    // Holds an instance of the DiscordBot class
    public DiscordBot bot;
    // Holds an instance of YouTube
    private YouTube youTube = null;

    // Constructor to assign an instance of the DiscordBot class to bot, constructs a DiscordEventListener object
    public DiscordEventListener(DiscordBot bot) {

        this.bot = bot;

        // Creates an instance of YouTube 
        try {
            this.youTube = new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), null)
                    .setApplicationName("Discord Bot").build();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Overrrides onMessageReceived whenever a message is sent in the discord channel
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        // Checks if the author is not a bot
        if(!event.getAuthor().isBot()) { // 
            String message = event.getMessage().getContentRaw();
            // Run searchYouTube method when user starts a message with "!play"
            if(message.startsWith("!play")) {
                // Extract the search query from the message
                String searchQuery = message.substring("!play".length()).trim();
                searchYouTube(searchQuery);
            }
        }

    }

    public void searchYouTube(String searchQuery) {

        // Create properties object to load YouTube API
        Properties properties = new Properties();

        try {
            // retrieve YouTube data API key
            InputStream input = new FileInputStream("config.properties");
            properties.load(input);
            String youTubeKey = properties.getProperty("youTubeKey");

            // perform search using YouTube data API
            YouTube.Search.List searchResults = youTube.search()
                    .list("id,snippet")
                    .setQ(searchQuery)
                    .setType("video")
                    .setKey(youTubeKey)
                    .execute()
                    .getItems();

            if(!searchResults.isEmpty()) {
                return "https://www.youtube.com/watch?v=" + searchResults.getId().getVideoID();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
