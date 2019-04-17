package com.xuxe.octaveBot.commands.miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;

public class HelpCommand extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
         String commands = "Ban users: `!!ban <@Mention>` \n" +
                            "Kick users: `!!kick <@Mention>` \n" +
                            "Calculate all your math stuff without ever leaving Discord: `!math <expression>`\n" +
                            "Get a random cat image: `!cat`\nGet user's avatar: `!avatar <@Mention>`\n" +
                            "Emotify sentences: `!Emotify <text>` \nAnti-suicidal Auto responses \n" +
                            "User join date: `!joinDate` \n" +
                            "Random facts: `!Mindfuck`\n" +
                            "Gathers information from Wikipedia infoboxes: `!info <term>` \n" +
                            "Server Creation date: `!serverCreationDate`\n" +
                            "See the FalconBot repository: `!rep` \n" +
                            "Google Search: `!g <terms>`\n" +
                            "See bot ping: `!ping` \n";

        User author = event.getAuthor();
        if (!author.hasPrivateChannel()) {
            author.openPrivateChannel().queue();
        }
        author.openPrivateChannel().queue((channel) ->
        {
            EmbedBuilder helpBed = new EmbedBuilder();
            helpBed.setTitle("FalconBot can||not|| do a lot");
            helpBed.setColor(Color.CYAN);
            helpBed.setDescription("");
            channel.sendMessage(helpBed.build()).queue();
            channel.sendMessage(commands).queue();
        });
        event.getChannel().sendMessage("Help has been cast to your DMs!").queue();
    }
    public HelpCommand() {
    this.name = "help";
    this.aliases = new String[]{"HELP", ""};
    this.help = "Shows more information about FalconBot's commands.";
        this.category = new Category("Miscellaneous");
}
}
