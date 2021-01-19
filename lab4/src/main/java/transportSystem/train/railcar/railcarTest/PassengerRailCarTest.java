package transportSystem.train.railcar.railcarTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.junit.*;

import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;
import transportSystem.train.ComfortLevel;
import transportSystem.train.railcar.PassengerRailCar;


@RunWith(MockitoJUnitRunner.class)
public class PassengerRailCarTest {

    PassengerRailCar testRC;

    Passenger passenger1 = mock(Passenger.class);
    Passenger passenger2 = mock(Passenger.class);

    Station destination = mock(TrainStation.class);

    Cargo luggage = mock(Cargo.class);


    @Before
    public void setup() {

        when(passenger1.getLuggage()).thenReturn(luggage);
        when(passenger2.getLuggage()).thenReturn(null);

        when(passenger1.getWeight()).thenReturn(10.);
        when(passenger2.getWeight()).thenReturn(20.);
        when(luggage.getWeight()).thenReturn(5.);
    }

    @Test
    public void railCarShouldAddPassengersAndLuggage() {

        testRC = new PassengerRailCar("test rail car", 10, 5, ComfortLevel.COMFORTABLE);

        assertEquals(0, testRC.countPassengers());

        testRC.addPassenger(passenger1);

        assertEquals(1, testRC.countPassengers());
        assertEquals(1, testRC.countLuggage());

        testRC.getEverythingOff();

        assertEquals(0, testRC.countPassengers());
        assertEquals(0, testRC.countLuggage());
    }

    @Test
    public void railCarShouldCorrectlyComputeWeight() {

        testRC = new PassengerRailCar("test rail car", 10, 5, ComfortLevel.COMFORTABLE);

        testRC.addPassenger(passenger1);
        testRC.addPassenger(passenger2);

        assertEquals(35.0, testRC.computeWeight(), 0.1);

    }

    @Test
    public void passengersShouldLeaveWithLuggageAtDestination() {

        testRC = new PassengerRailCar("test rail car", 10, 5, ComfortLevel.COMFORTABLE);

        Station otherDestination = mock(TrainStation.class);

        when(passenger2.getDestination()).thenReturn(destination);

        when(passenger1.getDestination()).thenReturn(otherDestination);

        testRC.addPassenger(passenger1);
        testRC.addPassenger(passenger2);

        testRC.leaveRailCar(destination);

        assertEquals(1, testRC.countPassengers());
        assertEquals(1, testRC.countLuggage());

        testRC.leaveRailCar(otherDestination);

        assertEquals(0, testRC.countPassengers());
        assertEquals(0, testRC.countLuggage());

        testRC.getEverythingOff();
    }

    @Test
    public void passengerShouldLeaveAtDepoOrTerminalStation() {

        testRC = new PassengerRailCar("test rail car", 10, 5, ComfortLevel.COMFORTABLE);

        Station depoMock = mock(Depo.class);
        Station terminalMock = mock(TerminalCargoStation.class);

        testRC.addPassenger(passenger2);
        testRC.leaveRailCar(depoMock);
        assertEquals(0, testRC.countPassengers());

        testRC.addPassenger(passenger1);
        testRC.leaveRailCar(terminalMock);
        assertEquals(0, testRC.countPassengers());
        assertEquals(0, testRC.countLuggage());
    }

    @Test
    public void railCarShouldntAddPassengersWhenOverloaded() {

        testRC = new PassengerRailCar("test rail car", 10, 5, ComfortLevel.COMFORTABLE);

        for (int i = 0; i < 5; i++) {

            Passenger passWLuggageMock = mock(Passenger.class);
            Cargo iterLuggMock = mock(Cargo.class);

            when(passWLuggageMock.getLuggage()).thenReturn(iterLuggMock);

            testRC.addPassenger(passWLuggageMock);
        }

        for (int i = 0; i < 7; i++) {

            Passenger passMock = mock(Passenger.class);
            when(passMock.getLuggage()).thenReturn(null);

            testRC.addPassenger(passMock);
        }

        assertEquals(5, testRC.countLuggage());
        assertEquals(10, testRC.countPassengers());
    }

}
