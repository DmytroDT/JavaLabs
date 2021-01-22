package menu.command;

import transportSystem.GlobalTrainSystem;

public class DeleteTrain extends Command {

    GlobalTrainSystem gb;

    public DeleteTrain(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        Integer val = tryParse(split(inputString)[0]);

        if (val != null) {
            gb.safeDisassembleTrain(val);
        } else {
            (new CommandError()).execute(inputString);
        }

    }

}