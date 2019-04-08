package com.xuxe.octaveBot.commands.admin;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.requests.RestAction;

import java.util.List;

public class PurgeMessages extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        MessageChannel messageChannel = event.getChannel();
        if(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            String[] command = event.getMessage().getContentRaw().split(" ");

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
    this.botPermissions = new Permission[Permission.MESSAGE_MANAGE.getOffset()];
        this.category = new Category("Moderation");
}
}
