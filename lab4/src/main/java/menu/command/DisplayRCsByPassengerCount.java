package menu.command;

import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class DisplayRCsByPassengerCount extends Command {

    GlobalTrainSystem gb;

    public DisplayRCsByPassengerCount(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) throws IOException, ClassNotFoundException {
        int TrainIndex;
        int lowerBound;
        int upperBound;

        try {
            TrainIndex = Integer.parseInt(split(inputString)[0]);
            lowerBound = Integer.parseInt(split(inputString)[1]);
            upperBound = Integer.parseInt(split(inputString)[2]);

            System.out.printf(gb.displayRailCarByPassengerCount(TrainIndex, lowerBound, upperBound));
        } catch (IndexOutOfBoundsException e) {

            (new CommandError()).execute(inputString);

        } catch (NumberFormatException e) {

            (new CommandError()).execute(inputString);

        }
    }

}
