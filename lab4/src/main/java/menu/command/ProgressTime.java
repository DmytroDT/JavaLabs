package menu.command;

import transportSystem.GlobalTrainSystem;

public class ProgressTime extends Command {

    GlobalTrainSystem gb;

    public ProgressTime(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) {

        Integer val = tryParseInt(split(inputString)[0]);

        if (val != null) {

            gb.progressTime(Integer.parseInt(split(inputString)[0]));

        } else {
            (new CommandError()).execute(inputString);
        }
    }
}