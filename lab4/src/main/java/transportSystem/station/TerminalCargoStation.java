package transportSystem.station;

import transportSystem.train.Train;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TerminalCargoStation extends Station implements Serializable {
//TODO: add toString
    List<Train> trainList = new ArrayList<Train>();

    public TerminalCargoStation() {
        setName("terminal cargo station");
    }

    @Override
    public void leave(Train train) {
        trainList.remove(train);
    }

    @Override
    public void arriveAt(Train train) {
        trainList.add(train);
    }

    public int countTrains(){
        return trainList.size();
    }
}
