package menu.command;

import transportSystem.GlobalTrainSystem;

public class DisplayAwaitingTrainPassengers extends Command{

    GlobalTrainSystem gb;

    public  DisplayAwaitingTrainPassengers(GlobalTrainSystem gb){
        this.gb=gb;
    }

    @Override
    public void execute(String inputString) {
        System.out.printf("\nStations list:\n");
        gb.printStationPassengers();
    }
}