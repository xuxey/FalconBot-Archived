package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.time.OffsetDateTime;

public class ServerCreationDate extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        OffsetDateTime creation = event.getGuild().getCreationTime();
        event.getChannel().sendMessage("On the glorious day of " + creation.getDayOfMonth() + " " + creation.getMonth() + ", " + creation.getYear() + " was " + event.getGuild().getName() + " born. And the people rejoiced. jk they died.").queue();
    }
    public ServerCreationDate() {
    this.name = "servercreationdate";
    this.aliases = new String[]{"serverdate", "guildbirthday"};
    this.help = "Gets the date on which the server was created.";
        this.category = new Category("Utility");
}
}
