package transportSystem.station;

import transportSystem.train.Train;
import java.util.*;

public abstract class Station {

    protected String stationName;
    protected List<Train> TrainList = new ArrayList<>();

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<Train> getTrainList() {
        return TrainList;
    }

    public void setTrainList(List<Train> trainList) {
        TrainList = trainList;
    }
}
