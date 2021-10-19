package transportSystem.station.stationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import transportSystem.station.Depo;
import transportSystem.train.Train;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DepoTest {

    Train train = mock(Train.class);

    Depo tD;

    @Test
    public void trainArrivePlaceholderTest() {

        tD = new Depo();

        tD.arriveAt(train);

        assertEquals(1, tD.countTrains());

    }

    @Test
    public void trainLeavePlaceholderTest() {

        tD = new Depo();

        tD.arriveAt(train);

        assertEquals(1, tD.countTrains());

        tD.leave(train);

        assertEquals(0, tD.countTrains());

    }

}
