package com.xuxe.octaveBot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.octaveBot.sql.Brownies;

public class BrownieCommand extends Command {
    public BrownieCommand()
    {
        this.name = "brownie";
        this.aliases = new String[]{"brownies","points"};
        this.help = "gets how many brownie points you have.";
        this.category = new Category("Fun");
    }

    @Override
    protected void execute(CommandEvent commandEvent)
    {
        Brownies brownies = new Brownies();
        commandEvent.reply("You have "+brownies.countBrownies(commandEvent.getAuthor().getId())+" brownies :D");
    }
}
