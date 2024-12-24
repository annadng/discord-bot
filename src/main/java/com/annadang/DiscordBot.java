package com.annadang;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.InputStream;

public class DiscordBot {

    public static void main(String[] args){

        Properties properties = new Properties();  // Create properties object

        try {
            InputStream input = new FileInputStream("config.properties"); // Opens config.properties to read
            properties.load(input); // Loads input contents into the properties object
            String apiKey = properties.getProperty("apiKey"); // Retrieve's apiKey stored in the config file

            JDA bot = JDABuilder.createDefault(apiKey) // Initialises bot with apiKey
                    .setActivity(Activity.playing("activity"))
                    .addEventListeners(new DiscordEventListener(new DiscordBot())) // Event listener that handles DiscordEventListener instances
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build().awaitReady(); // Builds the bot and waits until its fully connected before proceeding
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
