package com.annadang;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import net.dv8tion.jda.api.entities.Message;

public class DiscordEventListener extends ListenerAdapter {

    // holds an instance of the DiscordBot class
    public DiscordBot bot;

    // constructor to assign an instance of the DiscordBot class to bot, constructs a DiscordEventListener object
    public DiscordEventListener(DiscordBot bot) {

        this.bot = bot;
        YouTube youTube = null;

        try {
            youTube = new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), null)
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
                String query = message.substring("!play".length()).trim();
            }
        }

    }

}
