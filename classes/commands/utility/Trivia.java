package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Trivia extends Command
{
    private static final Logger logger = Logger.getGlobal();
    private EventWaiter waiter;
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        try
        {
            List<String> list = Files.readAllLines(Paths.get("trivia.txt"), StandardCharsets.ISO_8859_1);
            final String[] codes;
            EmbedBuilder trivia = new EmbedBuilder().setTitle("**Tr?via**");
            Random r = new Random();
            int randomNumber = r.ints(1, 0, list.size()-1).findFirst().getAsInt();
            File mind = new File("trivia.txt");
            Scanner sc = new Scanner(mind);
            codes = list.get(randomNumber).split("=");
            trivia.setDescription(codes[0]);
            commandEvent.getChannel().sendMessage(trivia.build()).queue
               (
                    e -> waiter.waitForEvent(
                            MessageReceivedEvent.class,
                            evt -> evt.getAuthor().equals(commandEvent.getAuthor()) && evt.getChannel().equals(commandEvent.getChannel()),
                            evt -> {
                                     String content = evt.getMessage().getContentRaw();
                                    if(content.toLowerCase().contains(codes[1].toLowerCase()))
                                        commandEvent.getChannel().sendMessage("Correct! You have won: 1 brownie point!").queue();
                                    else
                                        commandEvent.getChannel().sendMessage("Wrong! You get an F.").queue();
                                    },
                            15,
                            TimeUnit.SECONDS,
                            () -> commandEvent.reply("Sorry, you took too long.")),
                    e ->{}
               );


        }
        catch (IOException e)
        {
            commandEvent.reply("Error occurred.");
            e.printStackTrace();
        }
    }

    public Trivia(EventWaiter waiter)
    {
        this.aliases = new String[]{"triv","question"};
        this.name = "trivia";
        this.help = "Asks a trivia question.";
        this.cooldown = 10;
        this.cooldownScope = CooldownScope.USER;
        this.waiter = waiter;
        this.category = new Category("Utility");
    }
}
