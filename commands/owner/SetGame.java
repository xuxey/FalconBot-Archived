package com.xuxe.octaveBot.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Game;

public class SetGame extends Command
{
    public SetGame()
    {
        this.name = "setgame";
        this.ownerCommand = true;
        this.hidden = true;
        this.aliases = new String[]{"game"};
        this.category = new Category("Owner");
    }
    @Override
    protected void execute(CommandEvent commandEvent)
    {
        char game = commandEvent.getArgs().charAt(0);
        String content = commandEvent.getArgs().substring(1).trim();
        switch (game)
        {
            case 'p':commandEvent.getJDA().getPresence().setGame(Game.playing(content));
                     break;
            case 'l':commandEvent.getJDA().getPresence().setGame(Game.listening(content));
                     break;
            case 'w':commandEvent.getJDA().getPresence().setGame(Game.watching(content));
                     break;
            case 's':commandEvent.getJDA().getPresence().setGame(Game.streaming(content,null));
                     break;
            default:commandEvent.reply("Invalid Input.");
        }
        commandEvent.getMessage().addReaction("\u2705").queue();
    }
}
