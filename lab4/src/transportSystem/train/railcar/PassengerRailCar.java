package transportSystem.train.railcar;

import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.train.ComfortLevel;

import java.util.*;

public class PassengerRailCar extends RailCar implements Comparable<PassengerRailCar> {

    Map<Integer, Passenger> SeatedPassengers = new TreeMap<>();
    Map<Passenger, Cargo> LoadedLuggage = new HashMap<>();

    String type;
    int maxSeats;
    double maxBaggage;
    ComfortLevel comfortLevel;

    public PassengerRailCar() {
        type = "Standard passenger railcar";
        this.maxSeats = 54;
        this.maxBaggage = 25;
        this.comfortLevel = ComfortLevel.TOLERABLE;
    }

    public PassengerRailCar(String type, int maxSeats, double maxBaggage, ComfortLevel comfortLevel) {
        this.type = type;
        this.maxSeats = maxSeats;
        this.maxBaggage = maxBaggage;
        this.comfortLevel = comfortLevel;
    }

    boolean addPassenger(Passenger passenger) {

        if (!isOverloaded()) {
            return false;
        }
        for (int i = 1; i < maxSeats; i++) {
            if (!SeatedPassengers.containsKey(i)) {
                SeatedPassengers.put(i, passenger);
                break;
            }
        }
        return true;
    }

    void leaveRailCar(String stationName) {
        List<Integer> ReferenceSeats = new ArrayList<>();

        for (Map.Entry<Integer, Passenger> passenger : SeatedPassengers.entrySet()) {
            if (passenger.getValue().getDestination() == stationName) {
                ReferenceSeats.add(passenger.getKey());
            }
        }
//TODO: No luggage exception?
        for (Integer seat : ReferenceSeats) {
            Passenger Passenger = SeatedPassengers.get(seat);

            if (LoadedLuggage.containsKey(Passenger)) {
                LoadedLuggage.remove(Passenger);
            }

            SeatedPassengers.remove(seat);
        }

    }

    boolean loadLuggage(Passenger passenger, Cargo luggage) {
        if (LoadedLuggage.size() >= maxBaggage) {
            return false;
        }
        LoadedLuggage.put(passenger, luggage);
        return true;
    }

    @Override
    public double computeWeight() {
        double summaryWeight = 0;

        for (Map.Entry<Integer, Passenger> passenger : SeatedPassengers.entrySet()) {
            summaryWeight += passenger.getValue().getWeight();
        }
        for (Map.Entry<Passenger, Cargo> luggage : LoadedLuggage.entrySet()) {
            summaryWeight += luggage.getValue().getWeight();
        }
        return summaryWeight;
    }

    @Override
    public boolean isOverloaded() {
        return SeatedPassengers.size() > maxSeats;
    }

    @Override
    public int compareTo(PassengerRailCar otherRailCart) {
        return this.comfortLevel.compareTo(otherRailCart.comfortLevel);
    }

    public String getType() {
        return type;
    }
}
