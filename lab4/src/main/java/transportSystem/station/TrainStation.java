package transportSystem.station;

import transportSystem.baggage.Passenger;
import transportSystem.train.Train;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;

import java.io.Serializable;
import java.util.*;

public class TrainStation extends Station implements Serializable {

    List<Passenger> passengersList = new ArrayList<Passenger>();

    public TrainStation(String name, List<Passenger> passengersList) {
        this.setName(name);
        addPassengerList(passengersList);
    }

    public TrainStation(String name) {
        this.setName(name);
    }

    public void addPassenger(Passenger passenger) {
        passengersList.add(passenger);
    }

    public void addPassengerList(List<Passenger> passengers) {
        passengersList.addAll(passengers);
    }

    public void boardTrain(List<Station> remainingStations, List<RailCar> passengerRailCarList) {

        List<Passenger> passengersReference = new ArrayList<Passenger>();

        for (Passenger passenger : passengersList) {
            for (RailCar cart : passengerRailCarList) {

                if (cart instanceof PassengerRailCar) {

                    PassengerRailCar passRCRef = (PassengerRailCar) cart;

                    if (passenger.decideToBoard(passRCRef, remainingStations)) {
                        passRCRef.addPassenger(passenger);
                        passengersReference.add(passenger);
                        break;
                    }
                }
            }
        }

        passengersList.removeAll(passengersReference);
    }

    public boolean isStationEmpty() {
        return passengersList.isEmpty();
    }

    public int getPassengerCount() {
        return passengersList.size();
    }

    @Override
    public void arriveAt(Train train) {
        boardTrain(train.remainingStationsInRoute(), train.getRailCarts());
    }

    @Override
    public void leave(Train train) {

    }

    @Override
    public String toString() {

        String passengersOnStation = "";
        if (!passengersList.isEmpty()) {
            for (Passenger passenger : passengersList) {
                passengersOnStation += passenger.toString();
            }
        }

        return "TrainStation " + name + " has awaiting passengers " + passengersOnStation;
    }
}
