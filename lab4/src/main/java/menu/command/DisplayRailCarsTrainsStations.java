package menu.command;

import transportSystem.GlobalTrainSystem;

public class DisplayRailCarsTrainsStations extends Command {

    GlobalTrainSystem gb;

    public DisplayRailCarsTrainsStations(GlobalTrainSystem gb) {
        this.gb = gb;
    }


    @Override
    public void execute(String inputString) {
        (new DisplayStations(gb)).execute(inputString);
        (new DisplayRailCarts(gb)).execute(inputString);
        (new DisplayTrains(gb)).execute(inputString);
    }
}