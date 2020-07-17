package net.support.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public abstract class Command {

    String _name;
    String _description;
    String _usage;

    public Command(String name, String description, String usage) {
        _name = name;
        _description = description;
        _usage = usage;
    }

    abstract public void execute(Member user, Message message, String[] args);

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public String getUsage() {
        return _usage;
    }
}
