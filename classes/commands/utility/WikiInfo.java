package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WikiInfo extends Command {
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent) {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        String messageContent = commandEvent.getMessage().getContentRaw();
        MessageChannel messageChannel = commandEvent.getChannel();

        messageContent = messageContent.substring(messageContent.indexOf(" ")).trim();
        String backupTitle = messageContent;
        EmbedBuilder info = new EmbedBuilder().setColor(Color.cyan);
        String url = "http://en.wikipedia.org/wiki/" + messageContent.replace(' ', '_').trim();
        System.out.println("url:" + url);
        try {
            Connection.Response res = Jsoup.connect(url).execute();
            String html = res.body();
            Document doc2 = Jsoup.parseBodyFragment(html);

				/*Element body = doc2.body();
				Elements tables = body.getElementsByTag("table");
				for (Element table : tables) {
					if (table.className().contains("infobox")) {
							System.out.println(table.outerHtml());
						break;
					} //For testing purposes only.
				}*/
            try {
                String caption = doc2.select("caption").first().text();
                info.setTitle(caption);
            } catch (NullPointerException n)
            {
                info.setTitle(backupTitle);
            }
            java.util.List<String> subhead = new ArrayList<>();
            List<String> full = new ArrayList<>();
            int selectionsize = doc2.select("tr").size();
            if (selectionsize > 10) {
                selectionsize = 10;
            }
            for (Element e : doc2.select("tr").subList(0, selectionsize)) {
                //System.out.println(e.text());
                if (!e.outerHtml().contains("style=\"text-align:center;vertical-align:middle") && !e.outerHtml().contains("style=\"display:inline\"")) {
                    full.add(e.text().trim() + "\n");
                }
            }
            selectionsize = doc2.select("th").size();
            if (selectionsize > 10) {
                selectionsize = 10;
            }
            for (Element e : doc2.select("th").subList(0, selectionsize)) {
                //System.out.println(e.text());
                if (!e.outerHtml().contains("style=\"text-align:center;vertical-align:middle") && !e.outerHtml().contains("style=\"display:inline\"")) {
                    subhead.add(e.text().trim());
                }
            }
            String firstTerm = "";
            try {
                firstTerm = subhead.get(0);
            } catch (Exception e) {
                messageChannel.sendMessage("Couldn't find information. However, you can try " + url).queue();
            }
            int difference = 0, x = 0;
            while (!full.get(x).toLowerCase().startsWith(firstTerm.toLowerCase())) {
                difference++;
                if (x < full.size() - 1)
                    x++;
                else
                    break;
            }
            int c;
            descBuild:
            for (int k = 0; k < full.size(); k++) {
                c = 0;
                while (!full.get(c).toLowerCase().startsWith(subhead.get(k).toLowerCase()))
                {
                    c++;
                    if (c == full.size()) {
                        break descBuild;
                    }
                }
                String desc = "**" + subhead.get(k) + "** : " + full.get(c).substring(subhead.get(k).length());
                if (desc.length() > 70) {
                    desc = desc.substring(0, 70);
                    if (!desc.substring(0, desc.lastIndexOf(' ')).equalsIgnoreCase(""))
                        desc = desc.substring(0, desc.lastIndexOf(' ')) + "...";
                    info.appendDescription(desc + "\n");
                } else {
                    info.appendDescription(desc);
                }
            }
            String img;
            try {
                img = doc2.select("img").first().outerHtml();
                img = img.substring(img.indexOf("src=\"") + 7);
                img = img.substring(0, img.indexOf("\""));
                img = "https://" + img.trim();
                System.out.println("~~~~~~~" + img);
                info.setImage(img);
            } catch (NullPointerException | IllegalArgumentException argumentException) {
                info.setThumbnail("https://3c1703fe8d.site.internapcdn.net/newman/gfx/news/hires/2017/58af0228b8aa8.jpg");
            }
            info.appendDescription(url);
            messageChannel.sendMessage(info.build()).queue();
        } catch (HttpStatusException httpE) {
            messageChannel.sendMessage("Couldn't find anything, " + commandEvent.getAuthor().getAsMention()).queue();
        } catch (Exception e) {
            messageChannel.sendMessage("Something went wrong. ").queue();
            e.printStackTrace();
        }
    }
    public WikiInfo() {
        this.name = "info";
        this.aliases = new String[]{"chart", "wikipedia", "wiki", "information"};
        this.help = "Fetches data from existing Wikipedia infoboxes.";
        this.category = new Category("Utility");
        this.guildOnly=false;
    }
}
