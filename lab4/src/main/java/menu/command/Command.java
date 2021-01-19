package menu.command;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(String inputString) throws IOException, ClassNotFoundException;

    protected boolean checkIncorrectCommand(String input, int argumentsCount) {
        String[] splittedString = input.split(" ");
        return splittedString.length != argumentsCount;
    }

    protected String[] split(String input) {
        String[] splittedString = input.split(" ");
        return splittedString;
    }
}
