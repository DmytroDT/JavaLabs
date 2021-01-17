import org.junit.Test;
import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.*;
import transportSystem.train.ComfortLevel;
import transportSystem.train.Train;
import transportSystem.train.railcar.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;

public class TrainTest {

    List<Station> stations = new ArrayList<Station>();
    List<RailCar> railCars = new ArrayList<RailCar>();

    Train train;
    Station destination;
    Locomotive locomotive;


    @Before
    public void setup() {

        destination = new TerminalCargoStation();

        stations.add(new Depo());
        stations.add(new TrainStation("firstStation"));
        stations.add(new TrainStation("secondStation"));
        stations.add(new TrainStation("ThirdStation"));
        stations.add(destination);

        railCars.add(new PassengerRailCar());

        locomotive = new Locomotive();

    }

    void setupPassengers(){
        Cargo steelCase = new Cargo(10,5,"case");

        ((TrainStation)stations.get(3)).addPassenger(new Passenger(40, "Petro", stations.get(1), ComfortLevel.TOLERABLE));
        ((TrainStation)stations.get(1)).addPassenger(new Passenger(40, "Petro", stations.get(2), ComfortLevel.TOLERABLE));
        ((TrainStation)stations.get(1)).addPassenger(new Passenger(60, "Lara", stations.get(3), ComfortLevel.UNCOMFORTABLE,steelCase));

    }

    void moveTrain(int amountOfStops){
        for (int i = 0; i < amountOfStops; i++) {
            train.moveToNextStation();
        }
    }

    @Test
    public void trainShouldMoveToDestinationTest() {

        train = new Train("testTrain", railCars, stations, locomotive);

        moveTrain(4);

        assertEquals(destination, train.getStationReference());
    }

    @Test
    public void trainMustReturnCorrectNumberOfPassengersTest(){
        setupPassengers();
        train = new Train("testTrain", railCars, stations, locomotive);
        //At firstStation 2 passengers await train
        train.moveToNextStation();

        assertEquals(2,train.summaryPassengers());
        assertEquals(1,train.summaryLuggage());

    }

    @Test
    public void passengersShouldProperlyBoardAndLeaveTrainTest(){

        setupPassengers();
        train = new Train("testTrain", railCars, stations, locomotive);

        // 2 - 3 stations are station one 2 passengers destination, at station 3 train picks up passenger with destination of station 1
        moveTrain(3);
        assertEquals(1,train.summaryPassengers());
        assertEquals(0,train.summaryLuggage());
        // at Terminal station all luggage and passengers leave train
        moveTrain(1);
        assertEquals(0,train.summaryPassengers());

    }


}
