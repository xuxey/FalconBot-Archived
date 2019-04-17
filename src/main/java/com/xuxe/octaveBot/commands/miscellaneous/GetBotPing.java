package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GetBotPing extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        event.getChannel().sendMessage("Ping: " + event.getJDA().getPing()+"ms").queue();
    }
    public GetBotPing() {
    this.name = "ping";
    this.aliases = new String[]{"botping", "responsedelay"};
    this.help = "Fetches FalconBot's ping in milliseconds";
        this.category = new Category("Miscellaneous");
}
}
