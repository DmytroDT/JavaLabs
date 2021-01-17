package transportSystem.train;

import transportSystem.station.Station;
import transportSystem.station.TrainStation;
import transportSystem.train.railcar.CargoRailCar;
import transportSystem.train.railcar.Locomotive;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;

import java.util.*;

public class Train {

    String name;
    //TODO: change train collections structure to base + typecast methods
    List<RailCar> connectedRailCars = new ArrayList<RailCar>();
    List<PassengerRailCar> passengerRailCarList = new ArrayList<PassengerRailCar>();
    List<Station> routeStations = new ArrayList<Station>();
    Iterator<Station> currentStation;
    Station stationReference;
    Locomotive locomotiveReference;


    public Train(String name, List<RailCar> connectedRailCars, List<Station> routeStations, Locomotive locomotiveReference) {
        this.name = name;
        this.connectedRailCars = connectedRailCars;
        this.routeStations = routeStations;
        this.locomotiveReference = locomotiveReference;
        currentStation = this.routeStations.iterator();
        stationReference = currentStation.next();
        selectPassengerRailCars(connectedRailCars);
    }

    public double getTrainWeight() {
        double summaryWeight = 0;
        for (RailCar railCar : connectedRailCars) {
            summaryWeight += railCar.computeWeight();
        }
        return summaryWeight;
    }

    void ifOverloaded() {

        RailCar CargoReference = null;
        while (!locomotiveReference.isAbleToPush(this)) {
            for (RailCar railCar : connectedRailCars) {
                if (railCar instanceof CargoRailCar) {
                    CargoReference = railCar;
                }
            }
            connectedRailCars.remove(CargoReference);
        }
    }

    void selectPassengerRailCars(List<RailCar> railCars) {

        for (RailCar cart : railCars) {
            if (cart instanceof PassengerRailCar) {
                passengerRailCarList.add((PassengerRailCar) cart);
            }
        }
    }

    public void changeDirection() {
        Collections.reverse(routeStations);
        currentStation = routeStations.iterator();
        stationReference = currentStation.next();
    }

    public void moveToNextStation() {

        ifOverloaded();

        if (!currentStation.hasNext()) {
            changeDirection();
        }
        stationReference = currentStation.next();

        leaveRailCars();
        boardTrain();
    }

    void leaveRailCars() {
        for (PassengerRailCar cart : passengerRailCarList) {
            cart.leaveRailCar(stationReference);
        }
    }

    void boardTrain() {
        if (stationReference instanceof TrainStation) {
            ((TrainStation) stationReference).boardTrain(passengerRailCarList);
        }
    }

    public List<PassengerRailCar> sortAvailableCarsByComfort() {

        List<PassengerRailCar> referenceList = new ArrayList<PassengerRailCar>();

        Collections.copy(passengerRailCarList, referenceList);
        Collections.sort(referenceList);

        return referenceList;
    }

    public int summaryPassengers(){
        int summaryCount=0;
        for (PassengerRailCar cart : passengerRailCarList) {
            summaryCount+=cart.countPassengers();
        }
        return summaryCount;
    }

    public int summaryLuggage(){
        int summaryCount=0;
        for (PassengerRailCar cart : passengerRailCarList) {
            summaryCount+=cart.countLuggage();
        }
        return summaryCount;
    }

    public PassengerRailCar seekCarByPassengerNumbers(int lowerBound,int upperBound){
        int refCount;
        PassengerRailCar foundRailCar = null;

        for(PassengerRailCar cart:passengerRailCarList){
                refCount=cart.countPassengers();
                if((lowerBound<=refCount)&&(refCount<=upperBound)){
                    foundRailCar= cart;
                }
        }

        return  foundRailCar;
    }

    @Override
    public String toString() {
        String cartsName = "";
        String stationNames = "";

        for (RailCar cart : connectedRailCars) {
            cartsName += cart.getName() + ", ";
        }
        for (Station station : routeStations) {
            stationNames += station.getName() + ", ";
        }

        return "Train " + name + " powered by locomotive: " + ((RailCar) locomotiveReference).getName() +
                "\nconnected passenger carts: " + cartsName +
                "\nroute: " + stationNames +
                "\ncurrently on station: " + stationReference.getName();
    }

    public String displayRailCarts() {
        String cartsString = "";
        for (RailCar cart : connectedRailCars) {
            cartsString += "\n" + cart.toString();
        }
        return cartsString;
    }

    public Station getStationReference() {
        return stationReference;
    }
}
