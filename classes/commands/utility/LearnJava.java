package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.logging.Logger;

public class LearnJava extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Learn Java");
        embed.setDescription("\u200BBasic Java Tutorials: https://docs.oracle.com/javase/tutorial/\n" +
                "Online Java Course: https://www.codecademy.com/learn/learn-java \n" +
                "Object-Oriented programming in Java: http://mooc.fi/courses/2013/programming-part-1/");
        embed.setColor(Color.RED);
        commandEvent.getChannel().sendMessage(embed.build()).queue();
    }
    public LearnJava() {
    this.name = "learnjava";
    this.aliases = new String[]{"java", "programming"};
    this.help = "Fetches a series of helpful links to Java Tutorials";
    this.category = new Category("Utility");
        this.guildOnly=false;
}
}
