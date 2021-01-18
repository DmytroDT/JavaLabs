package menu.command;

import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class LegacyOldInit extends Command{

    GlobalTrainSystem gb;

    public  LegacyOldInit(GlobalTrainSystem gb){
        this.gb=gb;
    }

    @Override
    public void execute(String inputString) throws IOException, ClassNotFoundException {
        gb.oldInit();
    }
}