package transportSystem;

import org.apache.log4j.Logger;
import transportSystem.station.Station;
import transportSystem.train.Train;
import transportSystem.train.railcar.RailCar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TrainSystemFileManager {

    final static Logger logger = Logger.getLogger(TrainSystemFileManager.class);

    public void saveTrains(List<Train> trains) throws IOException {

        File savedTrains = new File("./saves/TrainSaveFile.txt");
        savedTrains.createNewFile();
        FileOutputStream trainsFileOutput = new FileOutputStream(savedTrains);
        ObjectOutputStream trainsObjectOutPut = new ObjectOutputStream(trainsFileOutput);
        trainsObjectOutPut.writeObject(trains);
        trainsObjectOutPut.close();
        trainsFileOutput.close();

    }

     List<Train> loadTrains() throws IOException, ClassNotFoundException {
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

     List<RailCar> loadRailCars() throws IOException, ClassNotFoundException {
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

     List<Station> loadStations() throws IOException, ClassNotFoundException {

        List<Station> stations;
        FileInputStream StationsFileInput= new FileInputStream("./saves/StationsSaveFile.txt");
        ObjectInputStream StationsObjectInput = new ObjectInputStream(StationsFileInput);

        stations = (ArrayList) StationsObjectInput.readObject();
        StationsObjectInput.close();
        StationsFileInput.close();

        return stations;
    }

    public List<Train>  safeLoadTrains(){

        List<Train> trains = new ArrayList<>();

        try{
            trains = loadTrains();

        }catch (InvalidClassException e){
                logger.info("Previously saved trains are incompatible with current program version.");
                logger.error(e.getMessage());
        }catch (ClassNotFoundException e){
                logger.info("Previous train save file not found.Create new saves.");
                logger.error(e.getMessage());
        }catch (IOException e){
            logger.info("Loading train save file failed.Please use loi instead or create new structure to save.");
            logger.error(e.getMessage());
        }

        return trains;
    }

    public List<RailCar>   safeLoadRailCars(){

        List<RailCar> railcars = new ArrayList<>();

        try{
            railcars = loadRailCars();

        }catch (InvalidClassException e){
            logger.info("Previously saved railcars are incompatible with current program version.");
            logger.error(e.getMessage());
        }catch (ClassNotFoundException e){
            logger.info("Previous railcar save file not found.Create new saves.");
            logger.error(e.getMessage());
        }catch (IOException e){
            logger.info("Loading train save file failed.Please use loi instead or create new structure to save.");
            logger.error(e.getMessage());
        }

        return railcars;
    }

    public List<Station>   safeLoadStations(){

        List<Station> stations = new ArrayList<>();

        try{
            stations = loadStations();
        }catch (InvalidClassException e){
            logger.info("Previously saved stations are incompatible with current program version.");
            logger.error(e.getMessage());
        }catch (ClassNotFoundException e){
            logger.info("Previous stations save file not found.Create new saves.");
            logger.error(e.getMessage());
        }catch (IOException e){
            logger.info("Loading train save file failed.Please use loi instead or create new structure to save.");
            logger.error(e.getMessage());
        }

        return stations;
    }



}
