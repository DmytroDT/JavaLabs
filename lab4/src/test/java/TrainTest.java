import  org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.*;

import transportSystem.station.*;
import transportSystem.train.Train;
import transportSystem.train.railcar.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TrainTest {

    Train train;

    Station stationMock = mock(Station.class);
    RailCar railCarMock = mock(PassengerRailCar.class);
    Locomotive locomotiveMock = mock(Locomotive.class);

    List<Station> stationsList = new ArrayList<Station>();
    List<RailCar> railCarList = new ArrayList<RailCar>();


    @Before
    public void setup() {

        stationsList = (List<Station>) Arrays.asList(stationMock,stationMock,stationMock);

        railCarList = (List<RailCar>) Arrays.asList(railCarMock,railCarMock,railCarMock);

        when(locomotiveMock.isAbleToPush(ArgumentMatchers.<Train>any())).thenReturn(true);

        when(((PassengerRailCar)railCarMock).countPassengers()).thenReturn(1);

        when(((PassengerRailCar)railCarMock).countLuggage()).thenReturn(0,0,1);

        when(railCarMock.computeWeight()).thenReturn(10000.1);

        train = new Train("tr", railCarList, stationsList,locomotiveMock);
    }

    @Test
    public void checkPassengerCount(){

        when(((PassengerRailCar)railCarMock).countPassengers()).thenReturn(15).thenReturn(20).thenReturn(30);

        assertEquals(railCarList.get(1), train.seekCarByPassengerNumbers(16,25));
    }

    void moveTrain(int amountOfStops) {
        for (int i = 0; i < amountOfStops; i++) {
            train.moveToNextStation();
        }
    }

    @Test
    public void trainShouldMoveToDestinationTest() {

        moveTrain(2);
        assertEquals(stationsList.get(1), train.getStationReference());
    }

   @Test
   public void trainMustReturnCorrectNumberOfPassengersTest() {

       assertEquals(3, train.summaryPassengers());
       assertEquals(1, train.summaryLuggage());
   }

   @Test
    public void trainMustReturnCorrectWeight(){
       assertEquals(railCarMock.computeWeight()*3,train.getTrainWeight(),0.1); ;
   }

}
