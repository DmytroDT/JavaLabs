package menu.command;

import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class DisplayRCsByComfort extends Command {

    GlobalTrainSystem gb;

    public DisplayRCsByComfort(GlobalTrainSystem gb) {
        this.gb = gb;
    }

    @Override
    public void execute(String inputString) throws IOException, ClassNotFoundException {

        Integer val = tryParseInt(split(inputString)[0]);

        if (val != null) {
            System.out.printf(gb.displayRailCarsByComfort(val));
        } else {
            (new CommandError()).execute(inputString);
        }
    }


}
