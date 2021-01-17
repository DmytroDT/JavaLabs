package transportSystem.station;

import transportSystem.baggage.Passenger;
import transportSystem.train.Train;
import transportSystem.train.railcar.PassengerRailCar;

import java.util.*;

public class TrainStation extends Station {

    List<Passenger> passengersList = new ArrayList<Passenger>();

    public TrainStation(String name, List<Passenger> passengersList) {
        this.setName(name);
        addPassengerList(passengersList);
    }

    public TrainStation(String name) {
        this.setName(name);
    }

    public  void addPassenger(Passenger passenger) {
        passengersList.add(passenger);
    }

    public  void addPassengerList( List<Passenger> passengers) {
        Collections.copy(passengers,passengersList);
    }

    public void boardTrain(List<PassengerRailCar> passengerRailCarList) {

        List<Passenger> passengersReference = new ArrayList<Passenger>();

        for (Passenger passenger : passengersList) {
            for (PassengerRailCar cart : passengerRailCarList) {

                if (passenger.satisfiedWithComfort(cart)) {
                    cart.addPassenger(passenger);
                    passengersReference.add(passenger);
                }
            }
        }

        for (Passenger passenger : passengersReference) {
            passengersList.remove(passenger);
        }
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }

    @Override
    public String toString() {

        String passengersOnStation="";
        for( Passenger passenger : passengersList){
            passengersOnStation+=passenger.toString();
        }

        return "TrainStation " + name +" has awaiting passengers " + passengersOnStation;
    }
}
