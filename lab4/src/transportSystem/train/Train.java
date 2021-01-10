package transportSystem.train;

import transportSystem.station.Station;
import transportSystem.train.railcar.CargoRailCar;
import transportSystem.train.railcar.Locomotive;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;

import java.util.*;

public class Train {
    String name;
    List<RailCar> ConnectedRailCars = new ArrayList<>();
    List<Station> RouteStations = new ArrayList<>();
    Iterator<Station> currentStation;
    Locomotive locomotiveReference;


    public Train(String name, List<RailCar> connectedRailCars, List<Station> routeStations, Locomotive locomotiveReference) {
        this.name = name;
        ConnectedRailCars = connectedRailCars;
        RouteStations = routeStations;
        this.locomotiveReference = locomotiveReference;
        currentStation = RouteStations.iterator();
    }

    public double getTrainWeight() {
        double summaryWeight = 0;
        for (RailCar railCar : ConnectedRailCars) {
            summaryWeight += railCar.computeWeight();
        }
        return summaryWeight;
    }

    void ifOverloaded() {
        RailCar CargoReference = null;
        while (locomotiveReference.isOverloaded()) {
            for (RailCar railCar : ConnectedRailCars) {
                if (railCar instanceof CargoRailCar) {
                    CargoReference = railCar;
                }
            }
            ConnectedRailCars.remove(CargoReference);
        }
    }

    void changeDirection() {
        Collections.reverse(RouteStations);
        currentStation = RouteStations.iterator();
    }

    void moveToNextStation() {
        Station stationReference = currentStation.next();
    }

    List<PassengerRailCar> sortByComfort() {
        List<PassengerRailCar> referenceList = new ArrayList<>();

        for (RailCar cart : ConnectedRailCars) {
            if (cart instanceof PassengerRailCar) {
                referenceList.add((PassengerRailCar) cart);
            }
        }

        Collections.sort(referenceList);
        return referenceList;
    }

}
