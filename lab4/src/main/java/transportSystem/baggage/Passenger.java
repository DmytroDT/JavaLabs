package transportSystem.baggage;

import transportSystem.station.Station;
import transportSystem.train.ComfortLevel;
import transportSystem.train.railcar.PassengerRailCar;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;

public class Passenger implements Serializable {

    double weight;
    String name;
    Station destination;
    ComfortLevel desiredComfort;
    Cargo luggage = null;

    public Passenger(double weight, String name, Station destination, ComfortLevel desiredComfort) {
        this.weight = weight;
        this.name = name;
        this.destination = destination;
        this.desiredComfort = desiredComfort;
    }

    public Passenger(double weight, String name, Station destination, ComfortLevel desiredComfort, Cargo luggage) {
        this.weight = weight;
        this.name = name;
        this.destination = destination;
        this.desiredComfort = desiredComfort;
        this.luggage = luggage;
    }

     boolean satisfiedWithComfort(PassengerRailCar cart) {
        return (this.getDesiredComfort().compareTo(cart.getComfortLevel()) <= 0) && (!cart.isOverloaded());
    }

    boolean comesThroughDestination(Iterator<Station> stationIterator){
        boolean hasDest = false;

        while (stationIterator.hasNext()){

            if(stationIterator.next()==destination){
                hasDest=true;
                break;
            }
        }
        return hasDest;
    }

    public boolean decideToBoard(PassengerRailCar cart,Iterator<Station> stationIterator){
        return satisfiedWithComfort(cart)&&comesThroughDestination(stationIterator);
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

    public Station getDestination() {
        return destination;
    }

    public ComfortLevel getDesiredComfort() {
        return desiredComfort;
    }

    @Override
    public String toString() {

        String holdingLuggage = "";
        if(luggage!=null){
            holdingLuggage="with luggage "+luggage.getName();
        }

        return "\nPassenger" + name + ", want to get to " + destination.getName() +
                " in a railcar of comfort " + desiredComfort  + holdingLuggage ;
    }

}
