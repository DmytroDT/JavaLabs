package menu.command;

import transportSystem.GlobalTrainSystem;

public class FillWithRandomPassengers extends Command {

    GlobalTrainSystem gb;

    public FillWithRandomPassengers(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        if (!checkIncorrectCommand(inputString, 1)) {
            gb.fillWithRandomPassengers(Integer.parseInt(split(inputString)[0]));
        } else {
            (new CommandError()).execute(inputString);
        }
    }
}