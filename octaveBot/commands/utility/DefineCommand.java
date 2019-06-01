package com.xuxe.octaveBot.commands.utility;

import com.google.gson.Gson;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.octaveBot.FalconConstants;
import net.dv8tion.jda.core.EmbedBuilder;
import org.jsoup.Jsoup;

import java.awt.*;

public class DefineCommand extends Command
{
    public DefineCommand()
    {
        this.name = "define";
        this.aliases = new String[]{"dictionary","words","definition", "word","def"};
        this.category = new Category("Utility");
        this.hidden = false;
        this.cooldown = 15;
        this.cooldownScope = CooldownScope.USER;
        this.guildOnly = false;
        this.help = "Gets the definition of a word.";
    }

    @Override
    protected void execute(CommandEvent commandEvent)
    {
        try {
            commandEvent.getChannel().sendTyping().queue();
            String args = commandEvent.getArgs().toLowerCase() + " ";
            Word word = generate(args.substring(0, args.indexOf(' ')).trim());
            if (!word.getMeta().isOffensive()) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                String id = word.getMeta().getId();
                embedBuilder.setTitle(id.substring(0, 1).toUpperCase() + id.substring(1));
                embedBuilder.setColor(Color.darkGray);
                embedBuilder.appendDescription("**" + word.getFl() + "**\n");
                embedBuilder.setFooter("Powered by Merriam Webster", FalconConstants.MERRIAM_ICON_URL);
                String[] words = word.getShortdef();
                for (int i = 0; i < words.length; i++)
                    embedBuilder.appendDescription("**" + (i + 1) + ".** _" + words[i] + "._\n");
                commandEvent.reply(embedBuilder.build());
            } else {
                commandEvent.reply("Sorry, you may not use offensive words.");
            }
        }
        catch (Exception e)
        {
            commandEvent.reply("Sorry, there was an issue.");
        }
    }
    private static Word generate(String word)
    {
        try
        {
            System.out.println("word: "+word);
            word = word.toLowerCase();
            String json = Jsoup.connect("https://www.dictionaryapi.com/api/v3/references/collegiate/json/"+word+"?key=" + FalconConstants.getMerriamKey()).ignoreContentType(true).execute().body();
            System.out.println(FalconConstants.getMerriamKey());
            Gson gson = new Gson();
            return gson.fromJson(json, Word[].class)[0];
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
class Word
{
    private String fl;
    private String[] shortdef;
    private meta meta;

    public Word(String fl, String[] shortdef, Word.meta meta) {
        this.fl = fl;
        this.shortdef = shortdef;
        this.meta = meta;
    }

    Word.meta getMeta() {
        return meta;
    }

    String getFl() {
        return fl;
    }

    String[] getShortdef() {
        return shortdef;
    }

    class meta
    {
        public String getId() {
            return id;
        }

        public boolean isOffensive() {
            return offensive;
        }

        String id;
        boolean offensive;
    }
}

