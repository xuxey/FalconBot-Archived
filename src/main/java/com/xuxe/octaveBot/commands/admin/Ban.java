package com.xuxe.octaveBot.commands.admin;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

import java.util.List;

public class Ban extends Command {
    public Ban() {
        this.name = "ban";
        this.aliases = new String[]{"BAN", "Ban"};
        this.help = "Bans mentioned account from the server";
        this.category = new Category("Moderation");
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        Guild guild = commandEvent.getGuild();

        if (guild == null) {
            commandEvent.reply("You must run this command in a server");
            return;
        }
        Member author = commandEvent.getMessage().getMember();

        if (!author.hasPermission(Permission.BAN_MEMBERS)) {
            commandEvent.reply("You don't have permission to ban!");
            return;
        }
        List<Member> mentionedMembers = commandEvent.getMessage().getMentionedMembers();
        if (mentionedMembers.isEmpty()) {
            commandEvent.reply("You must mention who you want to ban");
            return;
        }
        Member bannedUser = mentionedMembers.get(0);
        try {
            guild.getController().kick(bannedUser).queue(success -> commandEvent.reply(bannedUser.getEffectiveName() + " has been shown the door. forever."), error -> commandEvent.reply("Cannot kick this person."));
        } catch (Exception e) {
            commandEvent.reply("Cannot kick this person.");
        }
    }
}