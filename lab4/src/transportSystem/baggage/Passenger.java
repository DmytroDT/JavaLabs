package transportSystem.baggage;

import transportSystem.train.ComfortLevel;

public class Passenger {

    double weight;
    String name;
    String destination;
    ComfortLevel desiredComfort;

    public Passenger(double weight, String name, String destination, ComfortLevel desiredComfort) {
        this.weight = weight;
        this.name = name;
        this.destination = destination;
        this.desiredComfort = desiredComfort;
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
