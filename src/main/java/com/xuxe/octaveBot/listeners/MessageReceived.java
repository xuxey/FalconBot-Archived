package com.xuxe.octaveBot.listeners;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageReceived extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        final MessageChannel messageChannel = evt.getChannel();
        String messageContent = evt.getMessage().getContentRaw();
        User author = evt.getAuthor();
        if (messageContent.toLowerCase().contains("i wanna die") || messageContent.toLowerCase().contains("i want to die") || messageContent.contains("commit suicide") || messageContent.contains("kill myself")) {
            messageChannel.sendMessage("You dont have to suffer alone, " + author.getAsMention() + ". There is always hope. Suicide Prevention hotline: 1-800-273-8255").queue();
        }

    }
}
