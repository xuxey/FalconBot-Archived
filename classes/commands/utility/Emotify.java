package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.logging.Logger;

public class Emotify extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        String messageContent = commandEvent.getMessage().getContentRaw();
        messageContent = messageContent.substring(9).toLowerCase();
        if (messageContent.length() > 12)
            messageContent = messageContent.substring(0, 12);
        String t = "";
        int a;
        for (int i = 0; i < messageContent.length(); i++) {
            if (messageContent.charAt(i) == ' ') {
                t = t.concat(" ");
            }
            if ((int) messageContent.charAt(i) >= 97 && (int) messageContent.charAt(i) <= 122)
                t = t.concat(":regional_indicator_" + messageContent.charAt(i) + ":");
            else if ((int) messageContent.charAt(i) >= 48 && (int) messageContent.charAt(i) <= 57) {
                a = Integer.parseInt("" + messageContent.charAt(i));
                String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
                t = t.concat(numbers[a]);
            }
        }
        commandEvent.getChannel().deleteMessageById(commandEvent.getMessage().getId()).queue();
        commandEvent.getChannel().sendMessage(t).queue();
    }
    public Emotify() {
    this.name = "emotify";
    this.aliases = new String[] {"emojify"};
    this.help = "Converts an alpha numeric string into its emote equivalent. Limited to 12 characters.";
    this.category = new Category("Utility");
        this.guildOnly=false;
}
}
