package com.xuxe.octaveBot.listeners;

import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildVoiceLeave extends ListenerAdapter
{
    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        VoiceChannel channel = event.getChannelLeft();
        if(channel.getMembers().contains(event.getGuild().getMember(event.getJDA().getUserById("511949995776147466"))) && channel.getMembers().size()==1)
        {
            event.getGuild().getAudioManager().closeAudioConnection();
        }
    }
}
