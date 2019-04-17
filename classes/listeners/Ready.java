package com.xuxe.octaveBot.listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class Ready extends ListenerAdapter
{
    @Override
    public void onReady(ReadyEvent event) {
        User xuxe = event.getJDA().getUserById("451720586134290443");
        if (!xuxe.hasPrivateChannel()) {
            xuxe.openPrivateChannel().queue();
        }
        xuxe.openPrivateChannel().queue((channel) ->
        {
            EmbedBuilder startup = new EmbedBuilder();
            startup.setTitle("FalconBot has started.");
            startup.setColor(Color.DARK_GRAY);
            startup.setDescription("Guilds: " + event.getGuildAvailableCount() + "/" + event.getGuildTotalCount());
            startup.appendDescription("Ping: " + event.getJDA().getPing());
            channel.sendMessage(startup.build()).queue();
        });
    }
}
