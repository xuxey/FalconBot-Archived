package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class RandomFact extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        try {
            UsefulMethods usefulMethods = new UsefulMethods();
            Random r = new Random();
            int randomNumber = r.ints(1, 0, usefulMethods.textLineCounter("D:\\XuccBot's Folder\\XuccbotMindF.txt")).findFirst().getAsInt();
            File mind = new File("D:\\XuccBot's Folder\\XuccbotMindF.txt");
            Scanner sc = new Scanner(mind);
            int i = 0;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (i++ == randomNumber) {
                    EmbedBuilder mindF = new EmbedBuilder();
                    mindF.setTitle("MindFuck");
                    mindF.setDescription(s);
                    mindF.setColor(Color.red);
                    commandEvent.getChannel().sendMessage(mindF.build()).queue();
                    break;
                }
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("File not Found");
        }
        catch (IOException io)
        {
            commandEvent.getChannel().sendMessage("An exception has occured.").queue();
        }
    }

    public RandomFact() {
    this.name = "mindblow";
    this.aliases = new String[]{"facts", "randomfacts","mindfuck", "fact","randomfact"};
    this.help = "Gets a random fact";
        this.category = new Category("Utility");
        this.guildOnly=false;
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;

    }
}
