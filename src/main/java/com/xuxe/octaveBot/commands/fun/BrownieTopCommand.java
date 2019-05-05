package com.xuxe.octaveBot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.octaveBot.sql.Brownies;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrownieTopCommand extends Command
{
    public BrownieTopCommand()
    {
        this.name = "brownietop";
        this.aliases = new String[]{"leaderboard","brtop","topbrownie","topbrownies"};
        this.help = "Gets the top brownie collectors";
    }

    @Override
    protected void execute(CommandEvent commandEvent)
    {
        try
        {
            ResultSet resultSet = new Brownies().getTopBrownies(5);
            if(resultSet == null)
            {
                System.out.println("null :(");return;
            }
            EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("Brownie Leaderboard.").setColor(Color.black);
            int i = 1;
            JDA jda = commandEvent.getJDA();
            User user;
            while(resultSet.next())
            {
                user = jda.getUserById(resultSet.getString("uid"));
                String username,userDisc;
                try {
                     username = user.getName();
                    userDisc = user.getDiscriminator();
                }
                catch(NullPointerException npe) {
                    if (user == null) {
                        username = "InvalidUser";
                        userDisc = "0000";
                    }
                    else
                    {
                        commandEvent.reply("Something went wrong.");
                        return;
                    }
                }
                embedBuilder.appendDescription(i++ + ". "+username+"#"+userDisc+" - "+resultSet.getInt("trivpoints")+" Brownies\n");
            }
            commandEvent.reply(embedBuilder.build());
        } catch (SQLException e)
        {
            e.printStackTrace();
            commandEvent.reply("Sorry, fatal error occured.");
        }
    }
}
