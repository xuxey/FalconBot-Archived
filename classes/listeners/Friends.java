package com.xuxe.octaveBot.listeners;

import net.dv8tion.jda.client.events.relationship.FriendRequestReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Friends extends ListenerAdapter
{
    @Override
    public void onFriendRequestReceived(FriendRequestReceivedEvent event)
    {
        if(event.getFriendRequest().getUser().getId().equals("451720586134290443"))
        {
            event.getFriendRequest().accept().queue();
        }
    }
}
