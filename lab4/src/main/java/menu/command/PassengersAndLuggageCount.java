package menu.command;

import transportSystem.GlobalTrainSystem;

public class PassengersAndLuggageCount extends Command {

    GlobalTrainSystem gb;

    public PassengersAndLuggageCount(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        Integer val = tryParseInt(split(inputString)[0]);

        if (val != null) {
            System.out.printf(gb.safeTrainPassLuggCount(val));
        } else {
            (new CommandError()).execute(inputString);
        }
    }
}