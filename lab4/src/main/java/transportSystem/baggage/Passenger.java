package transportSystem.baggage;

import transportSystem.train.ComfortLevel;
import transportSystem.train.railcar.PassengerRailCar;

public class Passenger {

    double weight;
    String name;
    String destination;
    ComfortLevel desiredComfort;
    Cargo luggage = null;

    public Passenger(double weight, String name, String destination, ComfortLevel desiredComfort) {
        this.weight = weight;
        this.name = name;
        this.destination = destination;
        this.desiredComfort = desiredComfort;
    }

    public Passenger(double weight, String name, String destination, ComfortLevel desiredComfort, Cargo luggage) {
        this.weight = weight;
        this.name = name;
        this.destination = destination;
        this.desiredComfort = desiredComfort;
        this.luggage = luggage;
    }

    public boolean satisfiedWithComfort(PassengerRailCar cart) {
        return (this.getDesiredComfort().compareTo(cart.getComfortLevel()) <= 0) && (!cart.isOverloaded());
    }

    public Cargo getLuggage() {
        return luggage;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public ComfortLevel getDesiredComfort() {
        return desiredComfort;
    }
}
