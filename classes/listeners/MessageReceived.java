package com.xuxe.octaveBot.listeners;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.logging.Logger;

public class MessageReceived extends ListenerAdapter
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        final MessageChannel messageChannel = evt.getChannel();

        String messageContent = evt.getMessage().getContentRaw();
        User author = evt.getAuthor();
        if (messageContent.toLowerCase().contains("i wanna die") || messageContent.toLowerCase().contains("i want to die") || messageContent.contains("commit suicide") || messageContent.contains("kill myself")) {
            logger.info("user "+evt.getAuthor().getId()+" triggered anti-suicidal response in guild "+evt.getGuild().getId());
            messageChannel.sendMessage("You don't have to suffer alone, " + author.getAsMention() + ". There is always hope. Suicide Prevention hotline: 1-800-273-8255").queue();
        }

    }
}
