package menu.command;

import transportSystem.GlobalTrainSystem;

public class DisplayRCsTsSts extends Command {

    GlobalTrainSystem gb;

    public DisplayRCsTsSts(GlobalTrainSystem gb) {
        this.gb = gb;
    }


    @Override
    public void execute(String inputString) {
        (new DisplayStations(gb)).execute(inputString);
        (new DisplayRailCarts(gb)).execute(inputString);
        (new DisplayTrains(gb)).execute(inputString);
    }
}