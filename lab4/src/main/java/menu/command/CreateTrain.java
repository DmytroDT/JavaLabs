package menu.command;

import transportSystem.GlobalTrainSystem;

public class CreateTrain extends Command{

    GlobalTrainSystem gb;

    public  CreateTrain(GlobalTrainSystem gb){
        this.gb=gb;
    }

    @Override
    public void execute(String inputString) {

        if(!checkIncorrectCommand(inputString,1)){
            gb.createTrain(inputString);
        }else{
            (new CommandError()).execute(inputString);
        }

    }
}