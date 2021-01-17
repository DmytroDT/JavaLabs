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
    Iterator<Station> stationIterator;
    Station currentStation;
    Locomotive locomotiveReference;


    public Train(String name, List<RailCar> connectedRailCars, List<Station> routeStations, Locomotive locomotiveReference) {
        this.name = name;
        this.connectedRailCars = connectedRailCars;
        this.routeStations = routeStations;
        this.locomotiveReference = locomotiveReference;
        stationIterator = this.routeStations.iterator();
        currentStation = stationIterator.next();
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
        stationIterator = routeStations.iterator();
        currentStation = stationIterator.next();
    }

    public void moveToNextStation() {
        Station previousStation = currentStation;

        ifOverloaded();

        if (!stationIterator.hasNext()) {
            changeDirection();
        }
        currentStation = stationIterator.next();

        System.out.printf("\nTrain "+name+" moved from station "+ previousStation.getName()+" to station " + currentStation.getName()+
                " remaining passengers- "+summaryPassengers()+" ,remaining luggage- "+summaryLuggage()+".");

        leaveRailCars();
        boardTrain();
    }

    void leaveRailCars() {
        for (PassengerRailCar cart : passengerRailCarList) {
            cart.leaveRailCar(currentStation);
        }
    }

    void boardTrain() {
        if ((currentStation instanceof TrainStation)&&(!((TrainStation) currentStation).isStationEmpty())) {
            ((TrainStation) currentStation).boardTrain(passengerRailCarList);
        }
    }

    public String listSortedByComfortCarts() {

        Collections.sort(passengerRailCarList);
        String output="";

        for(PassengerRailCar cart: passengerRailCarList){
            output+="\n"+cart.toString();
        }

        return output;
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
                "\ncurrently on station: " + currentStation.getName();
    }

    public String displayRailCarts() {
        String cartsString = "";
        for (RailCar cart : connectedRailCars) {
            cartsString += "\n" + cart.toString();
        }
        return cartsString;
    }

    public Station getCurrentStation() {
        return currentStation;
    }
}
