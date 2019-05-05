package com.xuxe.octaveBot.commands.fun;

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

public class CatImage extends Command
{
    @Override
    protected void execute(CommandEvent event)
    {
        MessageChannel messageChannel = event.getChannel();
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
