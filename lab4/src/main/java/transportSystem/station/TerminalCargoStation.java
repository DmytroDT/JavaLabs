package transportSystem.station;

import transportSystem.train.Train;

import java.io.Serializable;

public class TerminalCargoStation extends Station implements Serializable {

    public TerminalCargoStation() {
        setName("terminal cargo station");
    }

    @Override
    public void leave(Train train) {

    }

    @Override
    public void arriveAt(Train train) {

    }
}
