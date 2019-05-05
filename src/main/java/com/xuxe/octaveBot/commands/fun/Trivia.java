package com.xuxe.octaveBot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.xuxe.octaveBot.sql.Brownies;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Trivia extends Command
{
    private EventWaiter waiter;
    @Override
    protected void execute(CommandEvent event)
    {
        Brownies brownies = new Brownies();
        try
        {
            EmbedBuilder trivia = new EmbedBuilder().setTitle("**Tr?via**");
            TriviaInstance triviaInstance = triviaHandler.generate();
            if(triviaInstance==null)
            {
                event.reply("Oops, something went wrong!");
                return;
            }
            trivia.setColor(Color.BLUE);
            trivia.setDescription(triviaInstance.getQuestion());
            if(triviaInstance.getType().equals("multiple"))
            {
                trivia.appendDescription("\n" + triviaInstance.getAll_answers());
            }
            else
            {
                trivia.appendDescription("True or False? ");
            }
            trivia.setFooter("Level: " + triviaInstance.getDifficulty()+" | "+triviaInstance.getCategory(),null);
            String answer = triviaInstance.getCorrect_answer();
            event.getChannel().sendMessage(trivia.build()).queue
               (
                    e -> waiter.waitForEvent(
                            MessageReceivedEvent.class,
                            evt -> evt.getAuthor().equals(event.getAuthor()) && evt.getChannel().equals(event.getChannel()),
                            evt -> {
                                     String content = evt.getMessage().getContentRaw();
                                    if(content.toLowerCase().contains(answer.toLowerCase())||content.toLowerCase().equalsIgnoreCase(""+triviaInstance.getCorrectAnswerLetter())) {
                                        brownies.addBrownie(evt.getAuthor().getId(), triviaInstance.getBrowniePoints());
                                        event.getChannel().sendMessage("Correct! You have won: "+triviaInstance.getBrowniePoints()+" brownie point(s)!").queue();
                                    }
                                    else {
                                        if(new Random().ints(1, (10 + 1)).limit(1).findFirst().getAsInt()>2)
                                            event.getChannel().sendMessage("Wrong! The answer was: "+answer+". You get an F.").queue();
                                        else
                                        {
                                            event.getChannel().sendMessage("Wrong!The answer was: "+answer+". You lose a brownie :(").queue();
                                            brownies.removeBrownie(evt.getAuthor().getId(), 1);
                                        }
                                    }
                                    },
                            15,
                            TimeUnit.SECONDS,
                            () -> event.reply("Sorry, you took too long.")),
                    e ->{}
               );


        }
        catch (Exception e)
        {
            event.reply("Error occurred.");
            e.printStackTrace();
        }
    }
    private TriviaHandler triviaHandler;
    public Trivia(EventWaiter waiter)
    {
        this.aliases = new String[]{"triv","question"};
        this.name = "trivia";
        this.help = "Asks a trivia question.";
        this.cooldown = 10;
        this.cooldownScope = CooldownScope.USER;
        this.waiter = waiter;
        this.category = new Category("Utility");
        triviaHandler = new TriviaHandler();
    }
}
