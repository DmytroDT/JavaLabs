import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import transportSystem.baggage.baggageTest.PassengerTest;
import transportSystem.station.stationTest.DepoTest;
import transportSystem.station.stationTest.TerminalCargoStationTest;
import transportSystem.station.stationTest.TrainStationTest;
import transportSystem.train.railcar.railcarTest.CargoRailCarTest;
import transportSystem.train.railcar.railcarTest.LocomotiveTest;
import transportSystem.train.railcar.railcarTest.PassengerRailCarTest;
import transportSystem.train.trainTest.ComfortLevelTest;
import transportSystem.train.trainTest.TrainTest;


public class TestRunner {

    final static Logger logger = Logger.getLogger(TestRunner.class);

    public static void main(String[] args) {
        Result res = JUnitCore.runClasses(TrainTest.class, ComfortLevelTest.class,
                PassengerRailCarTest.class, LocomotiveTest.class, CargoRailCarTest.class,
                TerminalCargoStationTest.class,TrainStationTest.class, DepoTest.class, PassengerTest.class);

        logger.info("Total number of tests: " + res.getRunCount() + "\nNumber of failed ones: " + res.getFailureCount());

        for (Failure failure : res.getFailures()) {
            logger.info(failure.getMessage());
        }

    }

}
