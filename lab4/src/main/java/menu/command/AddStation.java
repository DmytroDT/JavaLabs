package menu.command;

import transportSystem.GlobalTrainSystem;

public class AddStation extends Command {

    GlobalTrainSystem gb;

    public AddStation(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        String[] operatingStr = split(inputString);
        int index;
        int trainIndex = Integer.parseInt(operatingStr[0]);

        for (int i = 1; i < operatingStr.length; i++) {
            index = Integer.parseInt(operatingStr[i]);
            gb.trainAddStation(trainIndex, index);
        }

    }
}