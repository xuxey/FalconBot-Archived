package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;

public class Google extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        Guild guild = event.getGuild();
        MessageChannel messageChannel = event.getChannel();
        String messageContent = event.getMessage().getContentRaw();
        User author = event.getAuthor();
        if (guild.getId().equals("302467759823126529") && !(author.getId().equalsIgnoreCase("451720586134290443")) && !(guild.getMember(author).isOwner())) {
            messageChannel.sendMessage("Sorry, this feature is disabled in this guild.").queue();
        } else {
            String linkText;
            messageContent = messageContent.substring(messageContent.indexOf(" ")).trim();

            messageContent = messageContent.replace(' ', '+');
            messageContent = "https://www.google.com/search?q=" + messageContent.trim() + "&num=1";
            try {
                Document doc = Jsoup.connect(messageContent).userAgent("Mozilla/5.0").get();
                Element result = doc.select("h3.r > a").first();
                String linkHref = "" + result.attr("href");
                linkText = "" + result.text();
                System.out.println("!g LOG: " + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")) + " requested by " + author.getName());

                EmbedBuilder googleE = new EmbedBuilder();
                String desc = linkHref.substring(7, linkHref.indexOf("&"));
                if (desc.startsWith("?q=")) {
                    googleE.setColor(Color.RED);
                    googleE.setTitle("**Oops!**");
                    googleE.setDescription("Google couldn't get you anything worth reading!");
                    messageChannel.sendMessage(googleE.build()).queue();
                } else {
                    googleE.setColor(Color.ORANGE);
                    googleE.setTitle(linkText);
                    googleE.setDescription(desc.replace("%3Fv%3D","?v="));
                    messageChannel.sendMessage(googleE.build()).queue();
                }
            } catch (Exception e) {
                e.printStackTrace();
                messageChannel.sendMessage("Something went wrong! (Jsoup messed up lol)").queue();
            }
        }
    }
    public Google() {
    this.name = "g";
    this.aliases = new String[]{"google", "web", "search"};
    this.help = "Gets the first link from a google search.";
    this.category = new Category("Utility");
        this.guildOnly=false;
}
}
