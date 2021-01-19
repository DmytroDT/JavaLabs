package menu.command;

import transportSystem.GlobalTrainSystem;

public class DeleteTrain extends Command {

    GlobalTrainSystem gb;

    public DeleteTrain(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        if (!checkIncorrectCommand(inputString, 1)) {
            gb.disassembleTrain(Integer.parseInt(split(inputString)[0]));
        } else {
            (new CommandError()).execute(inputString);
        }

    }

}