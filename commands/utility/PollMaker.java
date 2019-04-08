package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class PollMaker extends Command
{
    @Override
    protected void execute(CommandEvent commandEvent) {
        String messageContent = commandEvent.getMessage().getContentRaw();
        messageContent = messageContent.substring(messageContent.indexOf(' '));
        String[] command = messageContent.split(":");
        int options = command.length-1;
        if(options>=3) {
            EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("Poll:");
            embedBuilder.setAuthor(commandEvent.getAuthor().getName());
            embedBuilder.setColor(Color.BLACK);
            embedBuilder.setDescription("**" + command[1] + "**" + (command[1].endsWith("?") ? "" : "?"));
            for (int i = 2; i < command.length; i++) {
                embedBuilder.appendDescription("\n" + command[i]);
            }
            commandEvent.getChannel().sendMessage(embedBuilder.build()).queue();
        }
        else
        {
            commandEvent.getChannel().sendMessage(this.help).queue();
        }
    }

    public PollMaker()
    {
        this.name = "poll";
        this.aliases = new String[]{"pollmaker", "election", "democracy"};
        this.help = "creates a poll for members to vote. `!!poll question:choice1:choice2:choice3...";
        this.cooldown = 60;
        this.category = new Category("Utility");
    }
}
