package menu.command;

import transportSystem.GlobalTrainSystem;

public class FillWithRandomPassengers extends Command {

    GlobalTrainSystem gb;

    public FillWithRandomPassengers(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        Integer val = tryParseInt(split(inputString)[0]);

        if (val != null) {
            gb.fillWithRandomPassengers(val);
        } else {
            (new CommandError()).execute(inputString);
        }
    }
}