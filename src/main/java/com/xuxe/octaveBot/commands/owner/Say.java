package com.xuxe.octaveBot.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Say extends Command
{
    public Say()
    {
        this.name = "say";
        this.aliases = new String[]{"Xuccbotsay"};
        this.ownerCommand = true;
        this.category = new Category("Owner");
    }
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        String content = commandEvent.getMessage().getContentRaw();
        String[] args = content.split(" ");
        MessageChannel messageChannel = commandEvent.getGuild().getTextChannelById(args[1]);
        content = content.substring(content.indexOf(' ')+1);
        content = content.substring(content.indexOf(' ')).trim();
        messageChannel.sendMessage(content).queue();
        commandEvent.getMessage().delete().queue();
    }
}
