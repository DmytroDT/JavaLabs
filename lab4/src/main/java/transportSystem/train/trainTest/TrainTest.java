package transportSystem.train.trainTest;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.*;

import transportSystem.baggage.Cargo;
import transportSystem.station.*;
import transportSystem.train.Train;
import transportSystem.train.railcar.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@RunWith(MockitoJUnitRunner.class)
public class TrainTest {

    Train train;

    Station stationMock1 = mock(Station.class);
    Station stationMock2 = mock(Station.class);
    Station stationMock3 = mock(Station.class);

    RailCar railCarMock = mock(PassengerRailCar.class);
    Locomotive locomotiveMock = mock(Locomotive.class);

    List<Station> stationsList = new ArrayList<Station>();
    List<RailCar> railCarList = new ArrayList<RailCar>();

    void moveTrain(Train train, int amountOfStops) {
        for (int i = 0; i < amountOfStops; i++) {
            train.moveToNextStation();
        }
    }


    @Before
    public void setup() {

        stationsList.add(stationMock1);
        stationsList.add(stationMock2);
        stationsList.add(stationMock3);

        railCarList.add(railCarMock);
        railCarList.add(railCarMock);
        railCarList.add(railCarMock);

        when(locomotiveMock.isAbleToPush(ArgumentMatchers.<Train>any())).thenReturn(true);

        when(((PassengerRailCar) railCarMock).countPassengers()).thenReturn(1);

        when(((PassengerRailCar) railCarMock).countLuggage()).thenReturn(0, 0, 1);

        when(railCarMock.computeWeight()).thenReturn(10000.1);

        train = new Train("tr", railCarList, stationsList, locomotiveMock);
    }

    @Test
    public void trainShouldFindCorrespondingPassengerCountRailCar() {

        when(((PassengerRailCar) railCarMock).countPassengers()).thenReturn(15).thenReturn(20).thenReturn(30);

        assertEquals(railCarList.get(1), train.seekCarByPassengerNumbers(16, 25));
    }

    @Test
    public void TrainShouldConnectRailCarsAndDiscardCargoRCifUnableToMove() {

        when(locomotiveMock.isAbleToPush(ArgumentMatchers.<Train>any())).thenReturn(false).thenReturn(false).thenReturn(true);

        List<RailCar> localRailCarList = new ArrayList<RailCar>();

        localRailCarList.addAll(railCarList);

        RailCar cargoRCMock = mock(CargoRailCar.class);

        Train localTrain = new Train("test", localRailCarList, stationsList, locomotiveMock);

        localTrain.connectRailCar(cargoRCMock);

        assertEquals(true, localRailCarList.contains(cargoRCMock));

        localTrain.moveToNextStation();

        assertEquals(false, localRailCarList.contains(cargoRCMock));

    }

    @Test
    public void trainShouldAddStationsToRoute() {

        List<Station> localStationList = new ArrayList<Station>();

        localStationList.addAll(stationsList);

        Station newStationMock = mock(TrainStation.class);

        Train localTrain = new Train("test", railCarList, localStationList, locomotiveMock);

        localTrain.addStationToRout(newStationMock);

        assertEquals(stationsList.get(2), localStationList.get(3));
    }

    @Test
    public void trainShouldMoveAndChangeDirection() {

        List<Station> localStationList = new ArrayList<Station>();

        localStationList.addAll(stationsList);

        Train localTrain = new Train("test", railCarList, localStationList, locomotiveMock);

        moveTrain(localTrain, 3);

        assertEquals(localTrain.getCurrentStation(), localStationList.get(1));

    }

    @Test
    public void trainShouldReturnProperStationIterator() {

        List<Station> localStationList = new ArrayList<Station>();

        localStationList.addAll(stationsList);

        Train localTrain = new Train("test", railCarList, localStationList, locomotiveMock);

        moveTrain(localTrain, 1);

        ListIterator<Station> refIter = stationsList.listIterator(1);

        assertEquals(refIter.next(),localTrain.getCurrentStationIterator().next());

    }

    @Test
    public void trainMustReturnCorrectNumberOfPassengersTest() {

        assertEquals(3, train.summaryPassengers());
        assertEquals(1, train.summaryLuggage());
    }

    @Test
    public void trainMustReturnCorrectWeight() {
        assertEquals(railCarMock.computeWeight() * 3, train.getTrainWeight(), 0.1);

    }

    @Test
    public void trainShouldCorrectlyDetermineCurrentStation() {

        Train localTrain = new Train("test", railCarList, stationsList, locomotiveMock);

        assertEquals(stationsList.get(0),localTrain.getCurrentStation());
    }

}
