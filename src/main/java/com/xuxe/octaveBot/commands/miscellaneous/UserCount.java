package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;

public class UserCount extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        long users = 0;
        for (Guild g : event.getJDA().getGuilds()) {
            users += g.getMembers().size();
        }
        event.getChannel().sendMessage("This bot has " + users + " users.").queue();
    }
    public UserCount() {
    this.name = "usercount";
    this.aliases = new String[]{"userCount", "UserCount", "users"};
    this.help = "Fetches the number of FalconBot users.";
        this.category = new Category("Miscellaneous");
}
}
