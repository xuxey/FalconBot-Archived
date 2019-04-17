package com.xuxe.octaveBot.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.ChannelType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Eval extends Command
{
    private ScriptEngine engine;
    public Eval()
    {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try
        {
            engine.eval("var imports = new JavaImporter(" +
                    "java.io," +
                    "java.lang," +
                    "java.util," +
                    "Packages.net.dv8tion.jda.core," +
                    "Packages.net.dv8tion.jda.core.entities," +
                    "Packages.net.dv8tion.jda.core.entities.impl," +
                    "Packages.net.dv8tion.jda.core.managers," +
                    "Packages.net.dv8tion.jda.core.managers.impl," +
                    "Packages.net.dv8tion.jda.core.utils);");
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }
        this.ownerCommand = true;
        this.name = "eval";
        this.help = "evaluates arguments passed to it.";
        this.category = new Category("Owner");
    }
    @Override
    protected void execute(CommandEvent event) {
        String messageContent = event.getMessage().getContentDisplay();
        if (!event.getAuthor().getId().equalsIgnoreCase("451720586134290443"))
        {
            event.reply("Sorry, this command is owner only!");
            return;
        }

        try
        {
            engine.put("event", event);
            engine.put("message", event.getMessage());
            engine.put("channel", event.getChannel());
            engine.put("args", event.getArgs());
            engine.put("api", event.getJDA());
            if (event.isFromType(ChannelType.TEXT))
            {
                engine.put("guild", event.getGuild());
                engine.put("member", event.getMember());
            }

            Object out = engine.eval(
                    "(function() {" +
                            "with (imports) {" +
                            messageContent.substring(messageContent.indexOf(' ')).trim() +
                            "}" +
                            "})();");
            //event.reply( out == null ? "Executed without error." : out.toString());
            event.getMessage().addReaction("\u2705").queue();
        }
        catch (Exception e1)
        {
            event.reply(e1.getMessage());
        }
    }
}

