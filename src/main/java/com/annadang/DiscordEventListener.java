package com.annadang;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class DiscordEventListener extends ListenerAdapter {

    // holds an instance of the DiscordBot class
    public DiscordBot bot;

    // constructor to assign an instance of the DiscordBot class to bot, constructs a DiscordEventListener object
    public DiscordEventListener(DiscordBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        // run this method if author is not a bot
        if(!event.getAuthor().isBot()) {
            String messageSent = event.getMessage().getContentRaw();
            // get text channel the message was sent in and queue the message to send back
            event.getChannel().sendMessage("This was sent: " + messageSent).queue();
        }

    }

}
