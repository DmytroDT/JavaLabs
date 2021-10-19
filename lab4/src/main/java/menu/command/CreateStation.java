package menu.command;

import transportSystem.GlobalTrainSystem;

public class CreateStation extends Command {

    GlobalTrainSystem gb;

    public CreateStation(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        gb.createNewStation(inputString);

    }
}