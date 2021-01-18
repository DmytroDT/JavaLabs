package menu.command;

import transportSystem.GlobalTrainSystem;

public class DisplayTrains extends Command{

    GlobalTrainSystem gb;

    public  DisplayTrains(GlobalTrainSystem gb){
        this.gb=gb;
    }

    @Override
    public void execute(String inputString) {
        System.out.printf("\nTrains list:\n");
        gb.printTrains();
    }
}