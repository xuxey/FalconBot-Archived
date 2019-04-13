package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.logging.Logger;

public class GuildCount extends Command {
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent) {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());        EmbedBuilder guilds = new EmbedBuilder();
        guilds.setTitle("Guild Count");
        guilds.setColor(Color.DARK_GRAY);
        guilds.setDescription("This bot is in " + commandEvent.getJDA().getGuilds().size() + " guilds.");
        commandEvent.getChannel().sendMessage(guilds.build()).queue();
    }
    public GuildCount() {
        this.name = "guildcount";
        this.aliases = new String[]{"guilds", "servercount", "servers"};
        this.help = "Gets the number of discord servers FalconBot is a part of.";

        this.category = new Category("Miscellaneous");
    }
}
