package menu.Commands;

import menu.Command;

public class CommandError extends Command {
    @Override
    public void execute(String inputString) {
        System.out.printf("Error, wrong command input");
    }
}
