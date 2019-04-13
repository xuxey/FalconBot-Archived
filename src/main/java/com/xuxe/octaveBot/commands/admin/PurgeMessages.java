package com.xuxe.octaveBot.commands.admin;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.requests.RestAction;

import java.util.List;
import java.util.logging.Logger;

public class PurgeMessages extends Command
{
    private static Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());        MessageChannel messageChannel = commandEvent.getChannel();
        if(commandEvent.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            String[] command = commandEvent.getMessage().getContentRaw().split(" ");

            if (command.length == 2) {
                RestAction<List<Message>> messageHistory = messageChannel.getHistory().retrievePast(Integer.parseInt(command[1]) + 1);
                List<Message> messages = messageHistory.complete();
                messageChannel.purgeMessages(messages);
            } else {
                messageChannel.sendMessage("Usage: !purge <number>").queue();
            }
        }
        else
        {
            messageChannel.sendMessage("You do not have permission to use this command.").queue();
        }
    }
    public PurgeMessages() {
    this.name = "purge";
    this.aliases = new String[]{"PURGE", "deleterecent"};
    this.help = "Deletes a fixed number of recent messages. Usage: `!!purge <number>`";
    this.category = new Category("Moderation");
}
}
