package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.time.OffsetDateTime;
import java.util.logging.Logger;

public class ServerCreationDate extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        OffsetDateTime creation = commandEvent.getGuild().getCreationTime();
        commandEvent.getChannel().sendMessage("On the glorious day of " + creation.getDayOfMonth() + " " + creation.getMonth() + ", " + creation.getYear() + " was " + commandEvent.getGuild().getName() + " born. And the people rejoiced. jk they died.").queue();
    }
    public ServerCreationDate() {
    this.name = "servercreationdate";
    this.aliases = new String[]{"serverdate", "guildbirthday"};
    this.help = "Gets the date on which the server was created.";
        this.category = new Category("Utility");
}
}
