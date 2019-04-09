package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Emotify extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        String messageContent = event.getMessage().getContentRaw();
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
        event.getChannel().deleteMessageById(event.getMessage().getId()).queue();
        event.getChannel().sendMessage(t).queue();
    }
    public Emotify() {
    this.name = "emotify";
    this.aliases = new String[]{"emote", "emojify"};
    this.help = "Converts an alpha numeric string into its emote equivalent. Limited to 12 characters.";
    this.category = new Category("Utility");
        this.guildOnly=false;
}
}
