package transportSystem.station;

import transportSystem.train.Train;

import java.io.Serializable;
import java.util.*;

public abstract class Station implements Serializable {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void arriveAt(Train train);

    public abstract void leave(Train train);



}
