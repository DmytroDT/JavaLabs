package transportSystem.station.stationTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;


import org.mockito.ArgumentMatchers;

import org.mockito.junit.MockitoJUnitRunner;

import transportSystem.baggage.Passenger;
import transportSystem.station.Station;
import transportSystem.station.TrainStation;
import transportSystem.train.Train;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class TrainStationTest {

    TrainStation trainStation;

    Train train = mock(Train.class);
    RailCar passRC = mock(PassengerRailCar.class);
    RailCar otherRC = mock(PassengerRailCar.class);

    Passenger pas1 = mock(Passenger.class);
    Passenger pas2 = mock(Passenger.class);
    Passenger leavingPass = mock(Passenger.class);

    Iterator<Station> stationIterator = mock(Iterator.class);

    List<Passenger> passengerList = new ArrayList<Passenger>();

    List<RailCar> railCarList = new ArrayList<RailCar>();

    @Before
    public void setup(){

        passengerList.add(pas1);
        passengerList.add(pas2);
        passengerList.add(leavingPass);

        railCarList.add(passRC);
        railCarList.add(otherRC);

        when(train.remainingStationsInRoute()).thenReturn(mock(List.class));

        when(train.getRailCarts()).thenReturn(railCarList);

        when(pas1.decideToBoard(ArgumentMatchers.<PassengerRailCar>any(),ArgumentMatchers.any())).thenReturn(false);
        when(pas2.decideToBoard(ArgumentMatchers.<PassengerRailCar>any(),ArgumentMatchers.any())).thenReturn(false);
        when(leavingPass.decideToBoard(((PassengerRailCar)passRC),train.remainingStationsInRoute())).thenReturn(true);
    }

    @Test
    public void PassengersShouldLeaveStationWhenBoardingTrain(){

        trainStation =  new TrainStation("test station",passengerList);

        trainStation.arriveAt(train);

        assertEquals(trainStation.getPassengerCount(),2);

    }

}
