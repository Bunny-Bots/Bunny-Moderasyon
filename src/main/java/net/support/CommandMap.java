package net.support;


import net.support.commands.*;

import java.util.HashMap;

final public class CommandMap {

    private static HashMap<String, Command> cmds = new HashMap<>();
    CommandMap(){
       registerCommand(new SetLogChannel());
       registerCommand(new ChannelSet());
       registerCommand(new SetMessage());
       registerCommand(new Tempmute());
       registerCommand(new Kick());
       registerCommand(new Ban());
       registerCommand(new Help());
       registerCommand(new CloseTicket());
    }

    public Command getCommand(String command) {
        return cmds.get(command);
    }
    public static HashMap<String, Command> getCommands() {
        return cmds;
    }

    public void registerCommand(Command cmd) {
        cmds.put(cmd.getName(), cmd);
    }
}
