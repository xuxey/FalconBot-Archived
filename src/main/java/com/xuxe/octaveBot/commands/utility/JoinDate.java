package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Member;

import java.time.OffsetDateTime;

public class JoinDate extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        Member member = event.getGuild().getMember(event.getAuthor());
        OffsetDateTime date = member.getJoinDate();
        event.getChannel().sendMessage("Your most recent join date is: " + date.getDayOfMonth() + " " + date.getMonth() + ", " + date.getYear()).queue();
    }
    public JoinDate() {
        this.name = "joindate";
        this.aliases = new String[]{"joinwhen"};
        this.help = "Gets most recent join date for a member in the server.";
        this.category = new Category("Utility");
    }
}
