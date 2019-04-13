package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Emote;

import java.util.logging.Logger;

public class EmoteInfo extends Command {
    private static final Logger logger = Logger.getGlobal();
    public EmoteInfo() {
        this.name = "emoji";
        this.aliases = new String[]{"emote","char","ch","emo"};
        this.guildOnly = false;
        this.help = "displays information about an emote.";
    }

    @Override
    public void execute(CommandEvent commandEvent) {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());        String str = commandEvent.getArgs().split(" ")[0];
        if(str.matches("<:.*:\\d+>"))
        {
            String id = str.replaceAll("<:.*:(\\d+)>", "$1");
            Emote emote = commandEvent.getJDA().getEmoteById(id);
            if(emote==null)
            {
                commandEvent.reply("Unknown emote:\n" +
                        "ID: **"+id+"**\n" +
                        "Guild: Unknown\n" +
                        "URL: https://discordcdn.com/emojis/"+id+".png");
                return;
            }
            commandEvent.reply("Emote **" + emote.getName() + "**:\n" +
                    "ID: **" + emote.getId() + "**\n" +
                    "Guild: " + (emote.getGuild() == null ? "Unknown" : "**" + emote.getGuild().getName() + "**") + "\n" +
                    "URL: " + emote.getImageUrl());
            return;
        }
        if(str.codePoints().count()>10)
        {
            commandEvent.reply("Error: Invalid emote/Input may be too long");
            return;
        }
        StringBuilder builder = new StringBuilder("Info: ");
        str.codePoints().forEachOrdered(code -> {
            char[] chars = Character.toChars(code);
            String hex = Integer.toHexString(code).toUpperCase();
            while(hex.length()<4)
                hex = "0"+hex;
            builder.append("\n`\\u").append(hex).append("`   ");
            if(chars.length>1)
            {
                String hex0 = Integer.toHexString(chars[0]).toUpperCase();
                String hex1 = Integer.toHexString(chars[1]).toUpperCase();
                while(hex0.length()<4)
                    hex0 = "0"+hex0;
                while(hex1.length()<4)
                    hex1 = "0"+hex1;
                builder.append("[`\\u").append(hex0).append("\\u").append(hex1).append("`]   ");
            }
            builder.append(String.valueOf(chars)).append("   _").append(Character.getName(code)).append("_");
        });
        commandEvent.reply(builder.toString());
    }
}
