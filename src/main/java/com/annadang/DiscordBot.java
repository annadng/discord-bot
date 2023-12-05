package com.annadang;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBot {

    public static void main(String[] args) {

        JDA bot = JDABuilder.createDefault("MTE4MTU1NTU0MTc1MTkwMjI3MA.GZKf-8.y3-YtyVIxheL5zxy3Sf6_K0togmZfRRA2ESdJc")
                .setActivity(Activity.playing(""))
                .build();

    }

}
