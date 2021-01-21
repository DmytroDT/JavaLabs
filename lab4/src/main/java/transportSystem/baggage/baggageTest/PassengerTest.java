package transportSystem.baggage.baggageTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.mockito.junit.*;


import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import transportSystem.baggage.Passenger;
import transportSystem.station.Station;
import transportSystem.train.ComfortLevel;
import transportSystem.train.railcar.PassengerRailCar;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PassengerTest {

    Passenger passenger;

    Station destination = mock(Station.class);
    Station otherStation = mock(Station.class);

    List<Station> remainingStations = new ArrayList<>();

    PassengerRailCar railCarMock = mock(PassengerRailCar.class);

    @Before
    public void setup(){

   remainingStations.add(destination);
   remainingStations.add(otherStation);

    }

    @Test
    public void passengerShouldBeSatisfiedWithProperConditions(){

        boolean result = false;
        passenger = new Passenger("test passenger",10.,destination, ComfortLevel.COMFORTABLE);


        when(railCarMock.getComfortLevel()).thenReturn(ComfortLevel.COMFORTABLE);

        result = passenger.decideToBoard(railCarMock,remainingStations);

        assertEquals(result,true);
    }

    @Test
    public void passengerShouldntBoardUncomfortableCart(){

        boolean result = true;

        passenger = new Passenger("test passenger",10.,destination, ComfortLevel.COMFORTABLE);

        when(railCarMock.getComfortLevel()).thenReturn(ComfortLevel.UNCOMFORTABLE);

        result = passenger.decideToBoard(railCarMock,remainingStations);

        assertEquals(result,false);

    }

    @Test
    public void passengerShouldntBoardTrainWithoutDestinationStation(){

        Station inExistingStation= mock(Station.class);
        boolean result = true;
        passenger = new Passenger("test passenger",10.,inExistingStation, ComfortLevel.COMFORTABLE);


        when(railCarMock.getComfortLevel()).thenReturn(ComfortLevel.COMFORTABLE);

        result = passenger.decideToBoard(railCarMock,remainingStations);

        assertEquals(result,false);

    }

}
