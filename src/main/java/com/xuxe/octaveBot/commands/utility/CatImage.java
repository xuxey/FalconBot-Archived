package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.awt.*;
import java.util.logging.Logger;

public class CatImage extends Command
{
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        MessageChannel messageChannel = commandEvent.getChannel();
            Unirest.get("http://aws.random.cat/meow").asJsonAsync(new Callback<JsonNode>() {
                @Override
                public void completed(HttpResponse<JsonNode> hr) {
                    messageChannel.sendMessage(new EmbedBuilder()
                            .setColor(Color.BLACK)
                            .setImage(hr.getBody().getObject().getString("file"))
                            .build()).queue();
                }

                @Override
                public void failed(UnirestException ue) {
                    messageChannel.sendMessage("The cats are asleep right now!").queue();
                }

                @Override
                public void cancelled() {
                    messageChannel.sendMessage("The cats don't like to see your face :(").queue();
                }
            });
    }
    public CatImage() {
    this.name = "cat";
    this.aliases = new String[]{"kitty", "pussy", "pussycat"};
    this.help = "Gets a random picture of a cat";
    this.cooldown = 6;
    this.cooldownScope = CooldownScope.USER;
    this.category = new Category("Utility");
        this.guildOnly=false;
}
}
