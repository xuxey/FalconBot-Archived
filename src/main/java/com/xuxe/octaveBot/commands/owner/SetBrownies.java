package com.xuxe.octaveBot.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.octaveBot.sql.Brownies;

public class SetBrownies extends Command {
    public SetBrownies()
    {
        this.aliases = new String[]{"setbrownie","setb"};
        this.name = "setbrownies";
        this.ownerCommand = true;
        this.hidden = true;
        brownies = new Brownies();
    }
    private Brownies brownies;
    @Override
    protected void execute(CommandEvent commandEvent)
    {
           String[] params = commandEvent.getArgs().trim().split(" ");
           try {
               System.out.println(params[0]+"~~~~~~~~~~~~~~~~~~`"+params[1]);
               brownies.setBrownie(params[0], Integer.parseInt(params[1]));
               commandEvent.getMessage().addReaction("\u2705").queue();
           }
           catch (Exception e)
           {
               commandEvent.reply("Error occured: "+e.getLocalizedMessage());
               e.printStackTrace();
           }
    }
}
