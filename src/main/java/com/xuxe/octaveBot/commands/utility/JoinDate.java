package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Member;

import java.time.OffsetDateTime;
import java.util.logging.Logger;

public class JoinDate extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        Member member = commandEvent.getGuild().getMember(commandEvent.getAuthor());
        OffsetDateTime date = member.getJoinDate();
        commandEvent.getChannel().sendMessage("Your most recent join date is: " + date.getDayOfMonth() + " " + date.getMonth() + ", " + date.getYear()).queue();
    }
    public JoinDate() {
        this.name = "joindate";
        this.aliases = new String[]{"joinwhen"};
        this.help = "Gets most recent join date for a member in the server.";
        this.category = new Category("Utility");
    }
}
