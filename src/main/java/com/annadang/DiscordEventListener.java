package com.annadang;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DiscordEventListener extends ListenerAdapter {

    // holds an instance of the DiscordBot class
    public DiscordBot bot;
    // holds an instance of YouTube
    private YouTube youTube = null;

    // constructor to assign an instance of the DiscordBot class to bot, constructs a DiscordEventListener object
    public DiscordEventListener(DiscordBot bot) {

        this.bot = bot;

        // creates an instance of YouTube
        try {
            this.youTube = new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), null)
                    .setApplicationName("Discord Bot").build();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        // run searchYouTube method when user starts a message with "!play"
        if(!event.getAuthor().isBot()) {
            String message = event.getMessage().getContentRaw();
            if(message.startsWith("!play")) {
                String searchQuery = message.substring("!play".length()).trim();
                searchYouTube(searchQuery);
            }
        }

    }

    public void searchYouTube(String searchQuery) {

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

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
