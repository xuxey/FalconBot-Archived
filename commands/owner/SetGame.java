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
    }
    @Override
    protected void execute(CommandEvent commandEvent)
    {
            String[] command = commandEvent.getMessage().getContentRaw().split(" ");
            String content = commandEvent.getMessage().getContentRaw().substring(commandEvent.getMessage().getContentRaw().indexOf(' '));
            switch (content.substring(0, content.indexOf(' ')))
            {
                case "playing": commandEvent.getJDA().getPresence().setGame(Game.playing(""));break;
                case "listening": commandEvent.getJDA().getPresence().setGame(Game.listening(""));break;
                case "watching": commandEvent.getJDA().getPresence().setGame(Game.watching(""));break;
            }
    }
}
