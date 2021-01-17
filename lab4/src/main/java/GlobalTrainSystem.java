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

        TrainStation st1 = new TrainStation(stationNames[0]);
        Station st2 = new TrainStation(stationNames[1]);
        Station st3 = new TrainStation(stationNames[2]);

        Station dp = new Depo();
        Station tm = new TerminalCargoStation();

        Cargo steelCase = new Cargo(10,5,"case");

        Passenger ps1 = new Passenger(40, "Petro", st3, ComfortLevel.TOLERABLE);
        Passenger ps2 = new Passenger(60, "Lara", st3, ComfortLevel.HEAVENLY,steelCase);
        Passenger ps3 = new Passenger(50, "Den", st3, ComfortLevel.UNCOMFORTABLE);

        passengers.add(ps1);
        passengers.add(ps2);
        passengers.add(ps3);

        stations.add(dp);
        stations.add(st1);
        stations.add(st2);
        stations.add(st3);
        stations.add(tm);

        st1.addPassenger(ps1);
        st1.addPassenger(ps2);
        st1.addPassenger(ps3);

        Locomotive locomotive = new Locomotive();

        RailCar kp = new PassengerRailCar("Kupe", 40, 40, ComfortLevel.TOLERABLE);
        RailCar stnd = new PassengerRailCar();
        RailCar lux = new PassengerRailCar("lux", 5, 10, ComfortLevel.HEAVENLY);

        railcars.add(kp);
        railcars.add(lux);
        railcars.add(stnd);

        Train train = new Train("parovoz", railcars, stations, locomotive);

        train.moveToNextStation();

        System.out.printf("Passengers: " + train.summaryPassengers()+" luggage"+train.summaryLuggage());

        train.moveToNextStation();

        System.out.printf("Passengers: " + train.summaryPassengers()+" luggage"+train.summaryLuggage());

    }

}
