package menu.command;

import transportSystem.GlobalTrainSystem;

public class PassengersAndLuggageCount extends Command {

    GlobalTrainSystem gb;

    public PassengersAndLuggageCount(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        Integer val = tryParse(split(inputString)[0]);

        if (val != null) {
            System.out.printf(gb.trainPassLuggCount(Integer.parseInt(split(inputString)[0])));
        } else {
            (new CommandError()).execute(inputString);
        }
    }
}