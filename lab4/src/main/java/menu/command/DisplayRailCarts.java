package menu.command;

import transportSystem.GlobalTrainSystem;

public class DisplayRailCarts extends Command {

    GlobalTrainSystem gb;

    public DisplayRailCarts(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {
        System.out.printf("\nRailCars list:\n");
        gb.printRailCars();
    }
}
