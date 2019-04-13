package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.logging.Logger;

public class GetBotPing extends Command
{
    private static final Logger logger = Logger.getGlobal();

    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        commandEvent.getChannel().sendMessage("Ping: " + commandEvent.getJDA().getPing()+"ms").queue();
    }
    public GetBotPing() {
    this.name = "ping";
    this.aliases = new String[]{"botping", "responsedelay"};
    this.help = "Fetches FalconBot's ping in milliseconds";
        this.category = new Category("Miscellaneous");
}
}
