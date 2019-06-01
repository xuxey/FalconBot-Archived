package com.xuxe.octaveBot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class MusicCommand extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        String messageContent = event.getMessage().getContentRaw();
        User author = event.getAuthor();
        MessageChannel messageChannel = event.getChannel();
        Guild guild = event.getGuild();
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
                        musicMethods.loadAndPlay(event.getTextChannel(), messageContent.substring(9).trim(), guild.getMember(author));
                    }
                    else if ("skip".equals(command[1]))
                    {
                        musicMethods.skipTrack(event.getTextChannel());
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

