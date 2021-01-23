package menu.command;

import transportSystem.GlobalTrainSystem;

public class AddRailCar extends Command {

    GlobalTrainSystem gb;

    public AddRailCar(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        String[] operatingStr = split(inputString);

        Integer trainIndex = tryParseInt(operatingStr[0]);
        Integer index;

        if (trainIndex == null) {
            (new CommandError()).execute("");
        } else {

            for (int i = 1; i < operatingStr.length; i++) {
                index = tryParseInt(operatingStr[i]);
                if (index != null) {
                    gb.safeTrainAddRC(trainIndex, index);
                }
            }
        }
    }

}
