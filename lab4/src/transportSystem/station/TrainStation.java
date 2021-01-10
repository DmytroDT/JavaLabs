package transportSystem.station;

import transportSystem.baggage.Passenger;
import transportSystem.baggage.Cargo;

import java.util.*;

public class TrainStation extends Station {

    List<Passenger> PassengersList = new ArrayList<>();
    Map<Passenger, Cargo> CarriedLuggage = new TreeMap<>();

    void addLuggage(Passenger Owner, Cargo Luggage) {
        CarriedLuggage.put(Owner, Luggage);
    }

    void addPassenger(Passenger passenger) {
        PassengersList.add(passenger);
    }

    public List<Passenger> getPassengersList() {
        return PassengersList;
    }
}
