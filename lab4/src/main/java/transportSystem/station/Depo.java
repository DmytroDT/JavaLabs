package transportSystem.station;

import transportSystem.train.Train;

import java.io.Serializable;

public class Depo extends Station implements Serializable {

    public Depo() {
        setName("Depo");
    }

    @Override
    public void arriveAt(Train train) {

    }

    @Override
    public void leave(Train train) {

    }
}
