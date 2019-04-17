package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jlyrics.Lyrics;
import com.jagrosh.jlyrics.LyricsClient;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.concurrent.ExecutionException;

public class SongInfo extends Command
{
    public SongInfo()
    {
        this.name = "song";
        this.aliases = new String[]{"lyrics","songs"};
        this.cooldown = 10;
        this.cooldownScope = CooldownScope.USER;
        this.help = "returns information about a song, along with a link to its lyrics.";
        this.category = new Category("Utility");
    }

    @Override
    protected void execute(CommandEvent commandEvent)
    {
        try
        {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            LyricsClient client = new LyricsClient();
            Lyrics lyrics = client.getLyrics(commandEvent.getArgs()).get();

            embedBuilder.setTitle("Music: "+lyrics.getAuthor().replace("Lyrics",""));
            embedBuilder.setColor(Color.GRAY);
            embedBuilder.setDescription("**"+lyrics.getTitle().replace('"',' ')+"**");
            embedBuilder.appendDescription(lyrics.getURL()+"\n");
            String finalLyrics = (lyrics.getContent().length()>600)?lyrics.getContent().substring(0,600):lyrics.getContent();
            embedBuilder.appendDescription(finalLyrics);
            embedBuilder.setFooter(lyrics.getSource(),null);

            commandEvent.reply(embedBuilder.build());

        } catch (InterruptedException e) {
            commandEvent.reply("An error has occurred: InterruptedException.");
            e.printStackTrace();
        } catch (ExecutionException e) {
            commandEvent.reply("An error has occurred: ExecutionException.");
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            commandEvent.reply("That song is either unavailable or you may have incorrectly spelled it.");
        }
        catch (Exception e)
        {
            commandEvent.reply("An error has occurred.");
            e.printStackTrace();
        }

    }
}
