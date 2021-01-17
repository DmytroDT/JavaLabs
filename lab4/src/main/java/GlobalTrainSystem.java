import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;
import transportSystem.train.ComfortLevel;
import transportSystem.train.Train;
import transportSystem.train.railcar.Locomotive;
import transportSystem.train.railcar.PassengerRailCar;
import transportSystem.train.railcar.RailCar;
import java.io.*;
import java.io.FileOutputStream;
import java.util.*;

public class GlobalTrainSystem {

    Random rnd = new Random();

    List<Train> trains = new ArrayList<Train>();
    List<RailCar> railcars = new ArrayList<RailCar>();
    List<Station> stations = new ArrayList<Station>();
    List<Cargo> cargo = new ArrayList<Cargo>();

    String[] passengerNames = {"Pavlo", "Dmytro", "Taras","Bogdan","Oleg"};
    String[] passengerSurnames = {" Pavluk", " Dovgal", " Jigal"," Kvas"," ProstoOleg"};

    void initialize(){

        stations.add(new Depo());
        stations.add(new TrainStation("Makova"));
        stations.add(new TrainStation("Holovan"));
        stations.add(new TrainStation("Koyota"));
        stations.add(new TrainStation("polyanova"));
        stations.add(new TerminalCargoStation());

        railcars.add( new PassengerRailCar("Kupe", 40, 40, ComfortLevel.UNCOMFORTABLE));
        railcars.add( new PassengerRailCar());
        railcars.add(  new PassengerRailCar("lux", 5, 10, ComfortLevel.HEAVENLY));

    }

    void createBaseTrains(){

        List<Station> route = Arrays.asList(stations.get(0),stations.get(1),stations.get(2),stations.get(3),stations.get(4),stations.get(5));
        List<RailCar> cars = Arrays.asList(railcars.get(0),railcars.get(1),railcars.get(2));
        trains.add(new Train ("Train 1",cars,route,new Locomotive()));

        //route = Arrays.asList(stations.get(0),stations.get(2),stations.get(4),stations.get(5));
        //cars = Arrays.asList(railcars.get(0),railcars.get(1),railcars.get(2));
        //trains.add(new Train ("Train 2",cars,route,new Locomotive()));

    }

    void saveTrains() throws IOException {
        if (!trains.isEmpty()) {
            File savedTrains = new File("TrainSaveFile.txt");
            savedTrains.createNewFile();
            FileOutputStream trainsFileOutput = new FileOutputStream(savedTrains);
            ObjectOutputStream trainsObjectoutput = new ObjectOutputStream(trainsFileOutput);
            trainsObjectoutput.writeObject(trains);
            trainsObjectoutput.close();
            trainsFileOutput.close();
        }
    }
    void loadTrains() throws IOException, ClassNotFoundException {
        FileInputStream trainsFileInput= new FileInputStream("TrainSaveFile.txt");
        ObjectInputStream trainsObjectInput = new ObjectInputStream(trainsFileInput);

        trains = (ArrayList) trainsObjectInput.readObject();
        trainsObjectInput.close();
        trainsFileInput.close();

        for(Train train : trains){
            train.returnToDepo();
        }
    }

    void fillWithRandomPassengers(int amountOfPassengers){

        int chosenStation=0;
        int chosenDestination=0;

        for(int i=0;i<amountOfPassengers;i++){
            do{
                chosenStation = randomIntInRange(1,stations.size()-2);
                chosenDestination= randomIntInRange(1,stations.size()-2);
            }while (chosenDestination==chosenStation);

            ((TrainStation)stations.get(chosenStation)).addPassenger(createRandomPassenger((TrainStation)stations.get(chosenDestination)));
        }
    }

    Passenger createRandomPassenger(Station destination){
        String passengerFullname = passengerNames[rnd.nextInt(5)]+passengerSurnames[rnd.nextInt(5)];;
        double passengerWeight=randomIntInRange(40,80);
        ComfortLevel comfortLevel = ComfortLevel.assignComfortLevel(randomIntInRange(0,100));
        Cargo luggage = null;
        if(rnd.nextBoolean()){
            luggage=new Cargo(10,10,"steel case");
        }
        return  new Passenger(passengerWeight,passengerFullname,destination,comfortLevel,luggage);
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
            System.out.printf("\n"+train.toString());
        }
    }

    String displayRailCarsByComfort(int trainNumber){

        return  trains.get(trainNumber).listSortedByComfortCarts();
    }

}
