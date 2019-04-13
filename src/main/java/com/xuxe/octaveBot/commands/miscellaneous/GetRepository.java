package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.logging.Logger;

public class GetRepository extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent) {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        commandEvent.getChannel().sendMessage("FalconBot's repository: https://github.com/ThatXuxe/FalconBot").queue();
    }
    public GetRepository() {
    this.name = "rep";
    this.aliases = new String[]{"repository", "sourcecode", "source"};
    this.help = "Fetches a link to FalconBot's GitHub repository.";
        this.category = new Category("Miscellaneous");
}
}
