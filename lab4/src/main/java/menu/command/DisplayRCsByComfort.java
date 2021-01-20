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

        Integer val = tryParse(split(inputString)[0]);

        if (val != null) {
            System.out.printf(gb.displayRailCarsByComfort(Integer.parseInt(split(inputString)[0])));
        } else {
            (new CommandError()).execute(inputString);
        }
    }


}
