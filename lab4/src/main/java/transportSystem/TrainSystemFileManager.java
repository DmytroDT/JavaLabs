package transportSystem;

import transportSystem.station.Station;
import transportSystem.train.Train;
import transportSystem.train.railcar.RailCar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TrainSystemFileManager {


    public void saveTrains(List<Train> trains) throws IOException {

        File savedTrains = new File("./saves/TrainSaveFile.txt");
        savedTrains.createNewFile();
        FileOutputStream trainsFileOutput = new FileOutputStream(savedTrains);
        ObjectOutputStream trainsObjectOutPut = new ObjectOutputStream(trainsFileOutput);
        trainsObjectOutPut.writeObject(trains);
        trainsObjectOutPut.close();
        trainsFileOutput.close();

    }

    public List<Train> loadTrains() throws IOException, ClassNotFoundException {
        List<Train> trains;

        FileInputStream trainsFileInput= new FileInputStream("./saves/TrainSaveFile.txt");
        ObjectInputStream trainsObjectInput = new ObjectInputStream(trainsFileInput);

        trains = (ArrayList) trainsObjectInput.readObject();
        trainsObjectInput.close();
        trainsFileInput.close();

        for(Train train : trains){
            train.reinitIterator();
        }
        return trains;
    }


    public void saveRailCars(List<RailCar> railcars) throws IOException {

        File savedRailCars = new File("./saves/RailCarsSaveFile.txt");
        savedRailCars.createNewFile();
        FileOutputStream RailCarsFileOutput = new FileOutputStream(savedRailCars);
        ObjectOutputStream RailCarsObjectOutPut = new ObjectOutputStream(RailCarsFileOutput);
        RailCarsObjectOutPut.writeObject(railcars);
        RailCarsObjectOutPut.close();
        RailCarsFileOutput.close();
    }

    public List<RailCar> loadRailCars() throws IOException, ClassNotFoundException {
        List<RailCar> railcars;
        FileInputStream RailCarsFileInput= new FileInputStream("./saves/RailCarsSaveFile.txt");
        ObjectInputStream RailCarsObjectInput = new ObjectInputStream(RailCarsFileInput);

        railcars = (ArrayList) RailCarsObjectInput.readObject();
        RailCarsObjectInput.close();
        RailCarsFileInput.close();

        return railcars;
    }

    public void saveStations(List<Station> stations) throws IOException {

        File savedStations = new File("./saves/StationsSaveFile.txt");
        savedStations.createNewFile();
        FileOutputStream StationsFileOutput = new FileOutputStream(savedStations);
        ObjectOutputStream StationsObjectOutPut = new ObjectOutputStream(StationsFileOutput);
        StationsObjectOutPut.writeObject(stations);
        StationsObjectOutPut.close();
        StationsFileOutput.close();
    }

    public List<Station> loadStations() throws IOException, ClassNotFoundException {

        List<Station> stations;
        FileInputStream StationsFileInput= new FileInputStream("./saves/StationsSaveFile.txt");
        ObjectInputStream StationsObjectInput = new ObjectInputStream(StationsFileInput);

        stations = (ArrayList) StationsObjectInput.readObject();
        StationsObjectInput.close();
        StationsFileInput.close();

        return stations;
    }


}