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

import java.util.Iterator;

@RunWith(MockitoJUnitRunner.class)
public class PassengerTest {

    Passenger passenger;

    Station destination = mock(Station.class);
    Station otherStation = mock(Station.class);

    Iterator<Station> stationIterator = mock(Iterator.class);

    PassengerRailCar railCarMock = mock(PassengerRailCar.class);

    @Before
    public void setup(){

    when(stationIterator.hasNext()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

    }

    @Test
    public void passengerShouldBeSatisfiedWithProperConditions(){

        boolean result = false;
        passenger = new Passenger("test passenger",10.,destination, ComfortLevel.COMFORTABLE);

        when(stationIterator.next()).thenReturn(otherStation).thenReturn(otherStation).thenReturn(destination);

        when(railCarMock.getComfortLevel()).thenReturn(ComfortLevel.COMFORTABLE);

        result = passenger.decideToBoard(railCarMock,stationIterator);

        assertEquals(result,true);
    }

    @Test
    public void passengerShouldntBoardUncomfortableCart(){

        boolean result = true;
        passenger = new Passenger("test passenger",10.,destination, ComfortLevel.COMFORTABLE);

        when(railCarMock.getComfortLevel()).thenReturn(ComfortLevel.UNCOMFORTABLE);

        result = passenger.decideToBoard(railCarMock,stationIterator);

        assertEquals(result,false);

    }

    @Test
    public void passengerShouldntBoardTrainWithoutDestinationStation(){

        boolean result = true;
        passenger = new Passenger("test passenger",10.,destination, ComfortLevel.COMFORTABLE);

        when(stationIterator.next()).thenReturn(otherStation).thenReturn(otherStation).thenReturn(otherStation);

        when(railCarMock.getComfortLevel()).thenReturn(ComfortLevel.COMFORTABLE);

        result = passenger.decideToBoard(railCarMock,stationIterator);

        assertEquals(result,false);

    }

}
