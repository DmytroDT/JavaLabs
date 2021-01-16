import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;
import transportSystem.train.ComfortLevel;
import transportSystem.train.Train;
import transportSystem.train.railcar.Locomotive;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;

import java.util.*;

public class GlobalTrainSystem {

    List<Train> trains = new ArrayList<Train>();
    List<RailCar> railcars = new ArrayList<RailCar>();
    List<Station> stations = new ArrayList<Station>();
    List<Passenger> passengers = new ArrayList<Passenger>();
    List<Cargo> cargo = new ArrayList<Cargo>();

    String[] stationNames = {"Makova", "Holovan", "Koyota"};


    public void testCase() {

        Passenger ps1 = new Passenger(40, "Petro", stationNames[1], ComfortLevel.TOLERABLE);
        Passenger ps2 = new Passenger(60, "Lara", stationNames[0], ComfortLevel.HEAVENLY);
        Passenger ps3 = new Passenger(50, "Den", stationNames[2], ComfortLevel.UNCOMFORTABLE);

        passengers.add(ps1);
        passengers.add(ps2);
        passengers.add(ps3);

        Station st1 = new TrainStation(stationNames[0], passengers);
        passengers.clear();
        passengers.add(ps2);
        passengers.add(ps3);
        Station st2 = new TrainStation(stationNames[1], passengers);
        Station dp = new Depo();
        Station tm = new TerminalCargoStation();

        stations.add(dp);
        stations.add(st1);
        stations.add(st2);
        stations.add(tm);

        Locomotive locomotive = new Locomotive();

        RailCar kp = new PassengerRailCar("Kupe", 40, 40, ComfortLevel.TOLERABLE);
        RailCar stnd = new PassengerRailCar();
        RailCar lux = new PassengerRailCar("lux", 5, 10, ComfortLevel.HEAVENLY);

        railcars.add(kp);
        railcars.add(lux);
        railcars.add(stnd);

        Train train = new Train("parovoz", railcars, stations, locomotive);
        locomotive.setTrainReference(train);


        for (int i = 0; i < 5; i++) {
            System.out.printf("\n\n" + train.toString());
            train.moveToNextStation();
        }

    }

}
