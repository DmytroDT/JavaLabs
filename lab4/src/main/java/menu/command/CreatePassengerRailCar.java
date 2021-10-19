package menu.command;

import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class CreatePassengerRailCar extends Command {

    GlobalTrainSystem gb;

    public CreatePassengerRailCar(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) throws IOException, ClassNotFoundException {
        String name=split(inputString)[0];
        int maxSeats;
        int maxBaggage;
        int comfortLevel;

        try {
            maxSeats = Integer.parseInt(split(inputString)[1]);
            maxBaggage = Integer.parseInt(split(inputString)[2]);
            comfortLevel = Integer.parseInt(split(inputString)[3]);

            gb.safeCreateNewPassengerRailCar(name,maxSeats, maxBaggage, comfortLevel);
        }  catch (NumberFormatException e) {
            (new CommandError()).execute(inputString);
        }
    }

}