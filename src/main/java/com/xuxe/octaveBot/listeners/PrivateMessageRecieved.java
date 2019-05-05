package com.xuxe.octaveBot.listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class PrivateMessageRecieved extends ListenerAdapter {
    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().getId().equalsIgnoreCase("451720586134290443")) {
            User author = event.getAuthor();
            PrivateChannel p = author.getJDA().getPrivateChannels().get(0);
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!guildList")) {
                for (Guild l : event.getJDA().getGuilds()) {
                    p.sendMessage(l.getName() + ", owned by: " + l.getOwner().getUser().getName() + "#" + l.getOwner().getUser().getDiscriminator()).queue();
                }
            }
        }
    }
}
