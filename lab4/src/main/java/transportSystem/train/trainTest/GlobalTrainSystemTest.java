package transportSystem.train.trainTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatchers;
import org.mockito.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import transportSystem.GlobalTrainSystem;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;

@RunWith(MockitoJUnitRunner.class)
public class GlobalTrainSystemTest {

    GlobalTrainSystem gb;

    Depo dep = mock(Depo.class);
    TerminalCargoStation tcs = mock(TerminalCargoStation.class);
    TrainStation ts = mock(TrainStation.class);
    Station st = mock(Station.class);



}
