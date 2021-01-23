package transportSystem;

import org.apache.log4j.Logger;
import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;
import transportSystem.train.ComfortLevel;
import transportSystem.train.Train;
import transportSystem.train.railcar.CargoRailCar;
import transportSystem.train.railcar.Locomotive;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;

import java.io.IOException;
import java.util.*;

public class GlobalTrainSystem {

    final static Logger logger = Logger.getLogger(GlobalTrainSystem.class);

    Random rnd = new Random();
    TrainSystemFileManager tFManager = new TrainSystemFileManager();

    List<Train> trains = new ArrayList<Train>();
    List<RailCar> railcars = new ArrayList<RailCar>();
    List<Station> stations = new ArrayList<Station>();
    List<Cargo> cargo = new ArrayList<Cargo>();


    String[] passengerNames = {"Pavlo", "Dmytro", "Taras", "Bogdan", "Oleg","Oleksandr","Andriy","Roman"};
    String[] passengerSurnames = {" Pavluk", " Dovgal", " Jigal", " Kvas", " Ilovaskiy"," Lepki"," Teodorovych"," Antkiv"};

    public GlobalTrainSystem(){
        stations.add(new Depo());
        stations.add(new TerminalCargoStation());
    }

    public void oldInit() {

        List<Station> route = new ArrayList<>();
        List<RailCar> cars = new ArrayList<>();

        stations.clear();
        railcars.clear();
        trains.clear();

        stations.add(new Depo());
        stations.add(new TrainStation("Makova"));
        stations.add(new TrainStation("Holovan"));
        stations.add(new TrainStation("Koyota"));
        stations.add(new TrainStation("polyanova"));
        stations.add(new TerminalCargoStation());

        railcars.add(new PassengerRailCar("Kupe", 40, 40, ComfortLevel.UNCOMFORTABLE));
        railcars.add(new PassengerRailCar());
        railcars.add(new PassengerRailCar("Lux", 5, 10, ComfortLevel.HEAVENLY));

        route.addAll(stations);
        trains.add(new Train("default train", cars, route, new Locomotive()));
        trainAddRailCart(0,0);
        trainAddRailCart(0,1);
        trainAddRailCart(0,2);
    }

    public void safeCreateNewPassengerRailCar(String name, int maxSeats, int maxBaggage, int comfortLevel){
       try {
           railcars.add(new PassengerRailCar(name,maxSeats,maxBaggage,comfortLevel) );
       }catch (IllegalArgumentException e){
           logger.info("Specified comfort level is out of bound.Please, set comfort level between 0 and 100.");
           logger.error(e.getMessage());
       }

    }

    public void createNewStation(String stationName){
        Station endStation = stations.get(stations.size()-1);
        stations.remove(endStation);
        stations.add(new TrainStation(stationName));
        stations.add(endStation);
    }

    //TODO: catch exceptions
    public void saveAll() throws IOException {
        tFManager.saveRailCars(railcars);
        tFManager.saveStations(stations);
        tFManager.saveTrains(trains);
    }

    public void loadAll() {
      //  railcars = tFManager.safeLoadRailCars();
        loadStationsAndTrains();
    }

    void loadStationsAndTrains(){
        stations = tFManager.safeLoadStations();
        trains = tFManager.completeLoadTrains(stations);
    }


    //TODO: when 2 depos or TCS are in array, throws exceptions.
    public void fillWithRandomPassengers(int amountOfPassengers) {

        int chosenStation = 0;
        int chosenDestination = 0;

        for (int i = 0; i < amountOfPassengers; i++) {
            do {
                chosenStation = rnd.nextInt(stations.size() - 1);
                chosenDestination = rnd.nextInt(stations.size() - 1);

            } while (matchingStationsCondition(stations.get(chosenStation),stations.get(chosenDestination)));

            ((TrainStation) stations.get(chosenStation)).
                    addPassenger(createRandomPassenger((TrainStation) stations.get(chosenDestination)));
        }
    }

    boolean instanceOfTerminalStations(Station station){
        return ((station instanceof Depo)||(station instanceof TerminalCargoStation));
    }

    boolean matchingStationsCondition(Station station1,Station station2){
        return ((station1.equals(station2))||(instanceOfTerminalStations(station1))||instanceOfTerminalStations(station2));
    }

    Passenger createRandomPassenger(Station destination) {

        String passengerFullName = passengerNames[rnd.nextInt(7)] + passengerSurnames[rnd.nextInt(7)];

        double passengerWeight = randomIntInRange(40, 80);
        ComfortLevel comfortLevel = ComfortLevel.assignComfortLevel(randomIntInRange(1, 100));
        Cargo luggage = null;

        if (rnd.nextBoolean()) {
            luggage = new Cargo(10, 10, "steel case");
        }

        return new Passenger(passengerFullName, passengerWeight, destination, comfortLevel, luggage);
    }

    int randomIntInRange(int min, int max) {
        int delta = max - min;
        return rnd.nextInt(delta + 1) + min;
    }

    public void progressTime(int amountOfHours) {

        for (int i = 0; i < amountOfHours; i++) {
            for (Train train : trains) {
                train.moveToNextStation();
                if(stations.contains(train.getCurrentStation())){
                    logger.info("everything's ok.");
                }else{
                    logger.info("Mismatch");
                }
            }
        }
    }

    public void printTrains() {
        for (Train train : trains) {
            System.out.printf(trains.indexOf(train) + ". " +
                    train.toString() + "\n");
        }
    }

    public void printRailCars() {
        for (RailCar car : railcars) {
            if (car instanceof PassengerRailCar) {
                System.out.printf(railcars.indexOf(car) + ". " +
                        ((PassengerRailCar) car).toString() + "\n");
            } else {
                System.out.printf(railcars.indexOf(car) + ". " +
                        ((CargoRailCar) car).toString() + "\n");
            }
        }
    }

    public void printStations() {
        for (Station station : stations) {
            System.out.printf(stations.indexOf(station) + ". " + station.getName() + "\n");
        }
    }

    public void printStationPassengers() {
        for (Station station : stations) {
            System.out.printf(stations.indexOf(station) + ". " + station.toString() + "\n");
        }
    }

    public String displayRailCarsByComfort(int trainNumber) {
        return trains.get(trainNumber).
                listSortedByComfortCarts();
    }

    public String displayRailCarByPassengerCount(int trainNumber, int lowerBound, int upperBound) {

        String output = "";
        PassengerRailCar railCarRef = null;
        railCarRef = trains.get(trainNumber).seekCarByPassengerNumbers(lowerBound, upperBound);

        if (railCarRef != null) {
            output = railCarRef.toString();
        } else {
            output = "Can't find railcars with passenger count in range [" + lowerBound + ";" + upperBound + "].";
        }
        return output;
    }

    public void clearTrains() {
        for (Train train : trains) {
            train.forceEverybodyOut();
        }
    }

    public void createTrain(String name) {
        List<Station> stationList = new ArrayList<Station>();
        List<RailCar> railCarList = new ArrayList<RailCar>();
        stationList.add(stations.get(0));
        stationList.add(stations.get(stations.size()-1));

        trains.add(new Train(name, railCarList, stationList, new Locomotive()));
    }

     void trainAddRailCart(int trainIndex, int cartIndex) {

        RailCar refRC = railcars.get(cartIndex);
        Train refTrain = trains.get(trainIndex);

        if (refRC instanceof PassengerRailCar) {
            refTrain.connectRailCar(new PassengerRailCar((PassengerRailCar) refRC));
        } else {
            refTrain.connectRailCar(new CargoRailCar((CargoRailCar) refRC));
        }
    }

     void trainAddStation(int trainIndex, int stationIndex) {
        trains.get(trainIndex).addStationToRout(stations.get(stationIndex));
    }

     void disassembleTrain(int trainIndex) {
        trains.remove(trainIndex);
    }

     String trainPassLuggCount(int trainIndex) {
        return trains.get(trainIndex).summaryOnboardObjects();
    }

    public void safeTrainAddRC(int trainIndex, int cartIndex){
        try{
            trainAddRailCart(trainIndex,cartIndex);
        }catch (IndexOutOfBoundsException e){
            logger.info("Specified index of train / railcar doesn't exist in collection.");
            logger.error(e.getMessage());
        }
    }

    public void safeTrainAddStations(int trainIndex, int stationIndex){
        try{
            trainAddStation(trainIndex,stationIndex);
        }catch(IndexOutOfBoundsException e){
            logger.info("Specified index of train / station doesn't exist in collection.");
            logger.error(e.getMessage());
        }
    }

    public void safeDisassembleTrain(int trainIndex){
        try {
            disassembleTrain(trainIndex);
        }catch (IndexOutOfBoundsException e){
            logger.info("Specified by index train doesn't exist.");
            logger.error(e.getMessage());
        }
    }

    public String safeTrainPassLuggCount(int trainIndex){
        String output ="";

        try {
            output=  trainPassLuggCount(trainIndex);
        }catch (IndexOutOfBoundsException e){
            logger.info("Specified by index train doesn't exist.");
            logger.error(e.getMessage());
        }
        return  output;
    }

}
