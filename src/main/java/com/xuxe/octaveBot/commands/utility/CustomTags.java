package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.octaveBot.main.Main;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomTags extends Command
{
    Connection connection;
    public CustomTags()
    {
        this.name = "tag";
        this.aliases = new String[]{"tags","tg"};
        this.category = new Category("Utility");
        this.help = "Stores text using a specific key that can be retrieved later.";
        this.cooldown = 60;
        this.cooldownScope = CooldownScope.USER;
        connection = new Main().getConnection();
    }
    @Override
    protected void execute(CommandEvent commandEvent) {
        try {
            if (commandEvent.getArgs().isEmpty()) {
                EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.BLACK).setTitle("Custom Tags");
                embedBuilder.setDescription("Tags are intuitive ways to store and retrieve batches of text easily. Think of a public clipboard, but in Discord.\n");
                embedBuilder.appendDescription("To get started, use `//tag create <name> <content>`\n");
                embedBuilder.appendDescription("Use `//tag <name>` to fetch a previously stored tag's contents.\n");
                embedBuilder.appendDescription("You may delete your tags using `//tag delete <name>`");
                embedBuilder.appendDescription("Do not use tags to store inappropriate content or content that violates Discord Terms Of Service.");
                commandEvent.reply(embedBuilder.build());
            } else {
                String tagName = (commandEvent.getArgs().split(" "))[0];
                PreparedStatement preparedStatement = connection.prepareStatement("select ? from ? where ;");
            }
        }
        catch (SQLException sqle)
        {
            commandEvent.reply("Sorry, I'm having Database issues!");
            sqle.printStackTrace();
        }
        catch (Exception e)
        {
            commandEvent.reply("Sorry, I'm having some pretty fatal issues!");
            e.printStackTrace();
        }
    }
}
