package com.xuxe.octaveBot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.logging.Logger;

public class MusicCommand extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        String messageContent = commandEvent.getMessage().getContentRaw();
        User author = commandEvent.getAuthor();
        MessageChannel messageChannel = commandEvent.getChannel();
        Guild guild = commandEvent.getGuild();
        MusicMethods musicMethods = new MusicMethods();
            if (messageContent.equalsIgnoreCase("!music"))
            {
                messageChannel.sendMessage("Usage: !music p <trackname>").queue();
            }
            else
            {
                String[] command = messageContent.split(" ");
                if (guild != null)
                {
                    if ("p".equals(command[1]))
                    {
                        musicMethods.loadAndPlay(commandEvent.getTextChannel(), messageContent.substring(9).trim(), guild.getMember(author));
                    }
                    else if ("skip".equals(command[1]))
                    {
                        musicMethods.skipTrack(commandEvent.getTextChannel());
                    }
                }
            }
    }
    public MusicCommand()
    {
        this.name="music";
        this.aliases=new String[]{"muzik","tunes"};
        this.help="Plays YouTube audio in voice channels. Usage: !music p <track name>. Use `!!music skip` to skip/pause tracks.";
        this.category = new Category("Music");
    }
}

