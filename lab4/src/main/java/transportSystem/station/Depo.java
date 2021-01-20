package transportSystem.station;

import transportSystem.train.Train;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Depo extends Station implements Serializable {

    List<Train> trainList = new ArrayList<Train>();

    public Depo() {
        setName("Depo");
    }

    @Override
    public void arriveAt(Train train) {
        trainList.add(train);
    }

    @Override
    public void leave(Train train) {
        trainList.remove(train);
    }

    public int countTrains(){
        return trainList.size();
    }
}
