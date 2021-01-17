package transportSystem.train.railcar;

import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;
import transportSystem.train.ComfortLevel;

import java.util.*;

public class PassengerRailCar extends RailCar implements Comparable<PassengerRailCar> {

    Map<Integer, Passenger> seatedPassengers = new TreeMap<Integer, Passenger>();
    Map<Passenger, Cargo> loadedLuggage = new HashMap<Passenger, Cargo>();

    int maxSeats;
    double maxBaggage;
    ComfortLevel comfortLevel;

    public PassengerRailCar() {
        setName("standard passenger railcar");
        this.maxSeats = 54;
        this.maxBaggage = 25;
        this.comfortLevel = ComfortLevel.TOLERABLE;
    }

    public PassengerRailCar(String name, int maxSeats, double maxBaggage, ComfortLevel comfortLevel) {
        this.setName(name);
        this.maxSeats = maxSeats;
        this.maxBaggage = maxBaggage;
        this.comfortLevel = comfortLevel;
    }

    public void addPassenger(Passenger passenger) {

        if (!isOverloaded()) {
            placeLuggage(passenger);
            seatPassenger(passenger);
        }

    }

    void placeLuggage(Passenger passenger) {

        if ((passenger.getLuggage() != null) && (loadedLuggage.size() <= maxBaggage)) {
            loadedLuggage.put(passenger, passenger.getLuggage());
        }
    }

    void seatPassenger(Passenger passenger) {
        for (int i = 1; i < maxSeats; i++) {
            if (!seatedPassengers.containsKey(i)) {
                seatedPassengers.put(i, passenger);
                break;
            }
        }
    }

    public void leaveRailCar(Station station) {

        if ((station instanceof Depo) || (station instanceof TerminalCargoStation)) {
            seatedPassengers.clear();
            loadedLuggage.clear();
        } else {
            offloadPassengers((TrainStation) station);
        }

    }

    void offloadPassengers(TrainStation station) {

        for (Integer seat : new ArrayList<Integer>(seatedPassengers.keySet())) {

            if (seatedPassengers.get(seat).getDestination() == station) {
                offloadLuggage( seatedPassengers.get(seat));
                seatedPassengers.remove(seat);
            }
        }

    }

    void offloadLuggage(Passenger passenger) {
        if (loadedLuggage.containsKey(passenger)) {
            loadedLuggage.remove(passenger);
        }
    }

    @Override
    public double computeWeight() {
        double summaryWeight = 0;

        for (Map.Entry<Integer, Passenger> passenger : seatedPassengers.entrySet()) {
            summaryWeight += passenger.getValue().getWeight();
        }
        for (Map.Entry<Passenger, Cargo> luggage : loadedLuggage.entrySet()) {
            summaryWeight += luggage.getValue().getWeight();
        }
        return summaryWeight;
    }

    @Override
    public boolean isOverloaded() {
        return seatedPassengers.size() > maxSeats;
    }

    @Override
    public int compareTo(PassengerRailCar otherRailCart) {
        return this.comfortLevel.compareTo(otherRailCart.comfortLevel);
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    @Override
    public String toString() {
        return "PassengerRailCar{" +
                "SeatedPassengers=" + seatedPassengers +
                ", LoadedLuggage=" + loadedLuggage +
                ", type='" + getName() + '\'' +
                ", comfortLevel=" + comfortLevel +
                ", cartWeight=" + cartWeight +
                ", cartMaxWeight=" + cartMaxWeight +
                '}';
    }
}
