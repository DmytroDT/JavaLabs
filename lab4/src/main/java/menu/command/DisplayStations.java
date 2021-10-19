package menu.command;

import transportSystem.GlobalTrainSystem;

public class DisplayStations extends Command {

    GlobalTrainSystem gb;

    public DisplayStations(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {
        System.out.printf("\nStations list:\n");
        gb.printStations();
    }
}