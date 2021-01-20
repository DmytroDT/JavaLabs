package transportSystem.station.stationTest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;


import org.mockito.ArgumentMatchers;

import org.mockito.junit.MockitoJUnitRunner;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.train.Train;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TerminalCargoStationTest {

    Train train = mock(Train.class);

    TerminalCargoStation tCS;

    @Test
    public void trainArrivePlaceholderTest(){

        tCS = new TerminalCargoStation();

        tCS.arriveAt(train);

        assertEquals(1, tCS.countTrains());

    }

    @Test
    public void trainLeavePlaceholderTest(){

        tCS = new TerminalCargoStation();

        tCS.arriveAt(train);

        assertEquals(1, tCS.countTrains());

        tCS.leave(train);

        assertEquals(0, tCS.countTrains());

    }

}
