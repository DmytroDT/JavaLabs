package menu.command;

import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class SaveAll extends Command{

    GlobalTrainSystem gb;

    public  SaveAll(GlobalTrainSystem gb){
        this.gb=gb;
    }

    @Override
    public void execute(String inputString) throws IOException {
        gb.saveAll();
    }
}