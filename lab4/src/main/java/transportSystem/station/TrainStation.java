package transportSystem.station;

import transportSystem.baggage.Passenger;
import transportSystem.train.Train;
import transportSystem.train.railcar.PassengerRailCar;

import java.util.*;

public class TrainStation extends Station {

    List<Passenger> PassengersList = new ArrayList<Passenger>();

    public TrainStation(String name, List<Passenger> passengersList) {
        this.setName(name);
        PassengersList = passengersList;
    }

    public TrainStation(String name) {
        this.setName(name);
    }

    void addPassenger(Passenger passenger) {
        PassengersList.add(passenger);
    }

    void fillTrains() {

        List<PassengerRailCar> railCArsReference = new ArrayList<PassengerRailCar>();

        for (Train train : this.TrainList) {
            railCArsReference = train.sortAvailableCarsByComfort();
            boardTrain(railCArsReference);
        }
    }

    public void boardTrain(List<PassengerRailCar> passengerRailCarList) {

        List<Passenger> passengersReference = new ArrayList<Passenger>();

        for (Passenger passenger : PassengersList) {
            for (PassengerRailCar cart : passengerRailCarList) {

                if (passenger.satisfiedWithComfort(cart)) {
                    cart.addPassenger(passenger);
                    passengersReference.add(passenger);
                }
            }
        }

        for (Passenger passenger : passengersReference) {
            PassengersList.remove(passenger);
        }
    }


    public List<Passenger> getPassengersList() {
        return PassengersList;
    }

}
