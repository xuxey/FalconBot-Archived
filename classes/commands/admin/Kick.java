package com.xuxe.octaveBot.commands.admin;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

import java.util.List;
import java.util.logging.Logger;

public class Kick extends Command {
        private static Logger logger = Logger.getGlobal();
        public Kick() {
            this.name = "kick";
            this.aliases = new String[]{"KICK", "kick", "byebye"};
            this.help = "Kicks mentioned account from the server";
            this.category = new Category("Moderation");
        }

        @Override
        protected void execute(CommandEvent commandEvent) {
            logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());            Guild guild = commandEvent.getGuild();

            if (guild == null) {
                commandEvent.reply("You must run this command in a server");
                return;
            }
            Member author = commandEvent.getMessage().getMember();

            if (!author.hasPermission(Permission.KICK_MEMBERS)) {
                commandEvent.reply("You don't have permission to kick people!");
                return;
            }
            List<Member> mentionedMembers = commandEvent.getMessage().getMentionedMembers();
            if (mentionedMembers.isEmpty()) {
                commandEvent.reply("You must mention who you want to be kicked");
                return;
            }
            Member kickedDude = mentionedMembers.get(0);
            try {
                guild.getController().kick(kickedDude).queue(success -> commandEvent.reply(kickedDude.getEffectiveName() + " has been shown the door."), error -> commandEvent.reply("Cannot kick this person."));
            } catch (Exception e) {
                commandEvent.reply("Cannot kick this person.");
            }
        }
    }
