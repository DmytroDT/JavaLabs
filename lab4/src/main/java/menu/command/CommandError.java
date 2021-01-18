package menu.command;

import transportSystem.GlobalTrainSystem;

public class CommandError extends Command {

    @Override
    public void execute(String inputString) {
        System.out.printf("Error, wrong command input. Type \"help\" in order to get help on available commands.");
    }
}
