package transportSystem.train.railcar.railcarTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.junit.*;
import transportSystem.train.Train;
import transportSystem.train.railcar.Locomotive;

@RunWith(MockitoJUnitRunner.class)
public class LocomotiveTest {

    Locomotive locomotive = new Locomotive();

    Train train = mock(Train.class);

    @Before
    public void setup() {
        when(train.getTrainWeight()).thenReturn(5000000.);
    }

    @Test
    public void locomotiveShouldntAbleToPushWhenOverloaded() {

        assertNotEquals(true, locomotive.isAbleToPush(train));

    }

}
