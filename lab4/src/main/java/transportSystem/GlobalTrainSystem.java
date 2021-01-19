package transportSystem;

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

    Random rnd = new Random();
    TrainSystemFileManager tFManager = new TrainSystemFileManager();

    List<Train> trains = new ArrayList<Train>();
    List<RailCar> railcars = new ArrayList<RailCar>();
    List<Station> stations = new ArrayList<Station>();
    List<Cargo> cargo = new ArrayList<Cargo>();


    String[] passengerNames = {"Pavlo", "Dmytro", "Taras","Bogdan","Oleg"};
    String[] passengerSurnames = {" Pavluk", " Dovgal", " Jigal"," Kvas"," ProstoOleg"};

    public void oldInit(){

            stations.add(new Depo());
            stations.add(new TrainStation("Makova"));
            stations.add(new TrainStation("Holovan"));
            stations.add(new TrainStation("Koyota"));
            stations.add(new TrainStation("polyanova"));
            stations.add(new TerminalCargoStation());

            railcars.add( new PassengerRailCar("Kupe", 40, 40, ComfortLevel.UNCOMFORTABLE));
            railcars.add( new PassengerRailCar());
            railcars.add(  new PassengerRailCar("lux", 5, 10, ComfortLevel.HEAVENLY));

            List<Station> route = Arrays.asList(stations.get(0),stations.get(1),stations.get(2),stations.get(3),stations.get(4),stations.get(5));
            List<RailCar> cars = Arrays.asList(railcars.get(0),railcars.get(1),railcars.get(2));
            trains.add(new Train ("Train 1",cars,route,new Locomotive()));
    }

    public void saveAll() throws IOException {
        tFManager.saveRailCars(railcars);
        tFManager.saveStations(stations);
        tFManager.saveTrains(trains);
    }

    public void loadAll() throws IOException, ClassNotFoundException {
        railcars =   tFManager.loadRailCars();
        stations =   tFManager.loadStations();
        trains =  tFManager.loadTrains();
    }

    public void fillWithRandomPassengers(int amountOfPassengers){

        int chosenStation=0;
        int chosenDestination=0;

        for(int i=0;i<amountOfPassengers;i++){
            do{
                chosenStation = randomIntInRange(1,stations.size()-2);
                chosenDestination= randomIntInRange(1,stations.size()-2);

            }while (chosenDestination==chosenStation);

            ((TrainStation)stations.get(chosenStation)).
                    addPassenger(createRandomPassenger((TrainStation)stations.get(chosenDestination)));
        }
    }

    Passenger createRandomPassenger(Station destination){

        String passengerFullName = passengerNames[rnd.nextInt(5)]+passengerSurnames[rnd.nextInt(5)];;
        double passengerWeight=randomIntInRange(40,80);
        ComfortLevel comfortLevel = ComfortLevel.assignComfortLevel(randomIntInRange(1,100));
        Cargo luggage = null;

        if(rnd.nextBoolean()){
            luggage=new Cargo(10,10,"steel case");
        }

        return  new Passenger(passengerFullName,passengerWeight,destination,comfortLevel,luggage);


    }

    int randomIntInRange(int min,int max){
        int delta=max-min;
        return rnd.nextInt(delta+1)+min;
    }

    public void progressTime(int amountOfHours){

        for(int i=0;i<amountOfHours;i++){
            for(Train train:trains){
                train.moveToNextStation();
            }
        }
    }

    public void printTrains(){
        for(Train train:trains){
            System.out.printf(trains.indexOf(train)+". "+
                    train.toString()+"\n");
        }
    }

    public void printRailCars(){
        for(RailCar car:railcars){
            if(car instanceof PassengerRailCar){
                System.out.printf(railcars.indexOf(car)+". "+
                        ((PassengerRailCar)car).toString()+"\n");
            }else{
                System.out.printf(railcars.indexOf(car)+". "+
                        ((CargoRailCar)car).toString()+"\n");
            }
        }
    }

    public void printStations(){
        for(Station station:stations){
            System.out.printf(stations.indexOf(station)+". "+station.getName()+"\n");
        }
    }

    public void printStationPassengers(){
        for(Station station:stations){
            System.out.printf(stations.indexOf(station)+". "+station.toString()+"\n");
        }
    }

    public String displayRailCarsByComfort(int trainNumber){
        return  trains.get(trainNumber).
                listSortedByComfortCarts();
    }

    public String displayRailCarByPassengerCount(int trainNumber,int lowerBound,int upperBound){

        String output="";
        PassengerRailCar railCarRef=null;
        railCarRef = trains.get(trainNumber).seekCarByPassengerNumbers(lowerBound,upperBound);

        if(railCarRef!=null){
            output=railCarRef.toString();
        }else{
            output="Can't find railcars with passenger count in range ["+lowerBound+";"+upperBound+"].";
        }
        return  output;
    }

    public void clearTrains(){
        for(Train train:trains){
            train.forceEverybodyOut();
        }
    }

    public void createTrain(String name){
        List<Station> stationList = new ArrayList<Station>();
        List<RailCar> railCarList = new ArrayList<RailCar>();
        stationList.add(new Depo());
        stationList.add(new TerminalCargoStation());

        trains.add(new Train(name,railCarList,stationList,new Locomotive()));
    }

    public void trainAddRailCart(int trainIndex,int cartIndex){

        if(railcars.get(cartIndex) instanceof PassengerRailCar){
            trains.get(trainIndex).
                    connectRailCar(new PassengerRailCar((PassengerRailCar)(railcars.get(cartIndex))));
        }else{
            trains.get(trainIndex).
                    connectRailCar(new CargoRailCar((CargoRailCar)(railcars.get(cartIndex))));
        }

    }

    public void trainAddStation(int trainIndex,int stationIndex){
        trains.get(trainIndex).
                addStationToRout(stations.get(stationIndex));
    }

    public void disassembleTrain(int trainIndex){
        trains.remove(trainIndex);
    }

    public String trainPassLuggCount(int trainIndex){
        return trains.get(trainIndex).summaryOnboardObjects();
    }

}
