package com.annadang;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.InputStream;

public class DiscordBot {

    public static void main(String[] args) throws LoginException {

        Properties properties = new Properties();

        try {
            InputStream input = new FileInputStream("config.properties");
            properties.load(input);
            String apiKey = properties.getProperty("apiKey");

            System.out.println(apiKey);

            JDA bot = JDABuilder.createDefault(apiKey)
                    .setActivity(Activity.playing("activity"))
                    .build();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
