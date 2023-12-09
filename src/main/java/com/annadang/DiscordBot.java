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

        Properties properties = new Properties();

        try {
            InputStream input = new FileInputStream("config.properties");
            properties.load(input);
            String apiKey = properties.getProperty("apiKey");

            JDA bot = JDABuilder.createDefault(apiKey)
                    .setActivity(Activity.playing("activity"))
                    .addEventListeners(new DiscordEventListener(new DiscordBot()))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
