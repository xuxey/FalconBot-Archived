package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.logging.Logger;

public class Avatar extends Command {
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());        String messageContent = commandEvent.getMessage().getContentRaw();
        User author = commandEvent.getAuthor();
        MessageChannel messageChannel = commandEvent.getChannel();
        System.out.println("~~~~~~"+messageContent);
        if (messageContent.equalsIgnoreCase("!!avatar")) {
            EmbedBuilder avatarEmbed = new EmbedBuilder().setColor(Color.darkGray).setTitle(author.getName() + "#" + author.getDiscriminator() + "'s avatar").setImage(author.getAvatarUrl());
            messageChannel.sendMessage(avatarEmbed.build()).queue();
        } else {
            User mentionedUser = commandEvent.getMessage().getMentionedMembers().get(0).getUser();
            EmbedBuilder avatarEmbed = new EmbedBuilder().setColor(Color.darkGray).setTitle(mentionedUser.getName() + "#" + mentionedUser.getDiscriminator() + "'s avatar").setImage(mentionedUser.getAvatarUrl());
            messageChannel.sendMessage(avatarEmbed.build()).queue();
        }
    }
    public Avatar() {
        this.name = "avatar";
        this.aliases = new String[]{"profile", "userimage"};
        this.help = "Gets a mentioned user's profile picture. Alternatively, gets sender's profile picture if no one is mentioned.";
        this.category = new Category("Utility");
        this.guildOnly=false;
    }
}
