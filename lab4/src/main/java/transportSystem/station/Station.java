package transportSystem.station;

import transportSystem.train.Train;

import java.io.Serializable;
import java.util.*;

public abstract class Station implements Serializable {

    protected String name;
    protected List<Train> TrainList = new ArrayList<Train>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Train> getTrainList() {
        return TrainList;
    }

    public void setTrainList(List<Train> trainList) {
        TrainList = trainList;
    }
}
