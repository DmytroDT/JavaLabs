package menu.command;

import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class LoadAll extends Command{

    GlobalTrainSystem gb;

    public  LoadAll(GlobalTrainSystem gb){
        this.gb=gb;
    }

    @Override
    public void execute(String inputString) throws IOException, ClassNotFoundException {
        gb.loadAll();
    }
}