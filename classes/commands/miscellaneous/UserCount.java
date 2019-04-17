package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;

import java.util.logging.Logger;

public class UserCount extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());        long users = 0;
        for (Guild g : commandEvent.getJDA().getGuilds()) {
            users += g.getMembers().size();
        }
        commandEvent.getChannel().sendMessage("This bot has " + users + " users.").queue();
    }
    public UserCount() {
    this.name = "usercount";
    this.aliases = new String[]{"userCount", "UserCount", "users"};
    this.help = "Fetches the number of FalconBot users.";
        this.category = new Category("Miscellaneous");
}
}
