package com.xuxe.octaveBot.commands.utility;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Math extends Command {
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        logger.info(name+" command used by "+commandEvent.getAuthor().getId()+" in "+commandEvent.getGuild().getId());
        MessageChannel messageChannel = commandEvent.getChannel();
        try {
            String messageContent = commandEvent.getMessage().getContentRaw();

            messageContent = messageContent.substring(6).trim();
            if (messageContent.length() <= 25) {
                messageContent = messageContent.replace("sqrt", "Math.sqrt");
                messageContent = messageContent.replace("pow", "Math.pow");
                messageContent = messageContent.replace("log", "Math.log");
                messageContent = messageContent.replace("sin", "Math.sin");
                messageContent = messageContent.replace("cos", "Math.cos");
                messageContent = messageContent.replace("tan", "Math.tan");
                messageContent = messageContent.replace("sec", "Math.acos");
                messageContent = messageContent.replace("cosec", "Math.asin");
                messageContent = messageContent.replace("cot", "Math.atan");
                messageContent = messageContent.replace("log10", "Math.log10");
                messageContent = messageContent.replace("pi", "Math.PI");
                messageContent = messageContent.replace("e", "Math.E");
                messageContent = messageContent.replace("×", "*");
                messageContent = messageContent.replace("÷", "/");
                if (messageContent.contains("Java")) {
                    messageChannel.sendMessage("use of keyword: `Java` is NOT allowed!").queue();
                    User author = commandEvent.getAuthor();
                    System.out.println(author.getName() + "#" + author.getDiscriminator() + " tried using Java in guild " + commandEvent.getGuild().getId() + " and channel " + messageChannel.getId() + " in message " + commandEvent.getMessage().getId());
                    throw new Exception("Illegal Math argument: Java.type");
                }
                while (messageContent.contains("^") || messageContent.contains("!")) {
                    if (messageContent.contains("^")) {
                        String part1 = messageContent.substring(0, messageContent.indexOf('^'));
                        String part2 = messageContent.substring(messageContent.indexOf('^') + 1);
                        messageContent = "Math.pow(" + part1 + "," + part2 + ");";
                    }
                    if (messageContent.contains("!")) {
                        String part1 = messageContent.substring(0, messageContent.indexOf('!'));
                        String part2 = messageContent.substring(messageContent.indexOf('!') + 1);
                        messageContent = "math.factorial(" + part1 + ")" + part2;
                    }
                }
                messageContent = messageContent + ";";
                System.out.println("Script:    " + messageContent);
                NashornSandbox sandbox = NashornSandboxes.create();
                //github.com/javadelight/delight-nashorn-sandbox
                sandbox.setMaxCPUTime(100);
                sandbox.setMaxPreparedStatements(0);
                sandbox.setExecutor(Executors.newSingleThreadExecutor());
                Object result = sandbox.eval(messageContent);
                if (result.toString().equalsIgnoreCase("NaN") || result.toString().equalsIgnoreCase("Infinity")) {
                    messageChannel.sendMessage(result.toString()).queue();
                } else {
                    Double res = Double.parseDouble(result.toString());
                    if (res - res.intValue() == 0) {
                        messageChannel.sendMessage("" + res.intValue()).queue();
                    } else {
                        messageChannel.sendMessage(result.toString()).queue();
                    }
                }
            } else {
                messageChannel.sendMessage("Your math expression is too long!").queue();
            }
        } catch (Exception e) {
            messageChannel.sendMessage("Something went wrong").queue();
            e.printStackTrace();
        }
    }
    public Math() {
        this.name = "math";
        this.aliases = new String[]{"mathematics", "maths", "calc", "calculate"};
        this.help = "Calculates a math expression";
        this.category = new Category("Utility");
        this.guildOnly=true;
    }
}
