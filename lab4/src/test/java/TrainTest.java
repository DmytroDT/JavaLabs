import org.junit.Test;
import transportSystem.station.*;
import transportSystem.train.ComfortLevel;
import transportSystem.train.Train;
import transportSystem.train.railcar.*;


import java.util.ArrayList;
import java.util.List;

import  static   org.junit.Assert.*;
import  static  org.junit.Before.*;
import org.junit.Before;

public class TrainTest {

    List<Station> stations = new ArrayList<Station>();
    List<RailCar> railCars = new ArrayList<RailCar>();

    Train train;
    Station destination;

    @Before
    public void  setup(){

        destination =  new TerminalCargoStation() ;

        stations.add(new Depo());
        stations.add(new TrainStation("firstStation"));
        stations.add(new TrainStation("secondStation"));
        stations.add(destination);

        railCars.add(new PassengerRailCar());

        Locomotive locomotive = new Locomotive();

        train = new Train("testTrain",railCars,stations,locomotive);
        locomotive.setTrainReference(train);
    }

    @Test
    public void TrainShouldMoveToDestinationTest(){

        for(int i =0;i<3;i++){
            train.moveToNextStation();
        }
        assertEquals(destination,train.getStationReference());
    }



}
