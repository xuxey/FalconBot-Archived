package com.xuxe.octaveBot.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;

public class ListGuilds extends Command {
    @Override
    protected void execute(CommandEvent event) {
        if (event.getAuthor().getId().equalsIgnoreCase("451720586134290443")) {
            User author = event.getAuthor();
            PrivateChannel p = author.getJDA().getPrivateChannels().get(0);
            int i = 0;
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!guildList"))
            {
                for (Guild l : event.getJDA().getGuilds()) {
                    p.sendMessage(++i+". "+l.getName() + ", owned by: " + l.getOwner().getUser().getName() + "#" + l.getOwner().getUser().getDiscriminator()+" with guild ID: "+l.getId()).queue();
                }
            }
            event.getMessage().addReaction("\u2705").queue();
        }
    }

    public ListGuilds() {
        this.name = "guildlist";
        this.ownerCommand = true;
        this.guildOnly = false;
        this.hidden = true;
        this.aliases = new String[]{"listguilds","glist"};
    }
}
