package transportSystem.train.railcar;

import org.apache.log4j.Logger;
import transportSystem.baggage.Cargo;
import transportSystem.baggage.Passenger;
import transportSystem.station.Depo;
import transportSystem.station.Station;
import transportSystem.station.TerminalCargoStation;
import transportSystem.station.TrainStation;
import transportSystem.train.ComfortLevel;

import java.io.Serializable;
import java.util.*;

public class PassengerRailCar extends RailCar implements Comparable<PassengerRailCar> , Serializable {

    final static Logger logger = Logger.getLogger(PassengerRailCar.class);

    Map<Integer, Passenger> seatedPassengers = new TreeMap<Integer, Passenger>();
    Map<Passenger, Cargo> loadedLuggage = new HashMap<Passenger, Cargo>();

    int maxSeats;
    double maxLuggage;
    ComfortLevel comfortLevel;

    public PassengerRailCar() {
        setName("standard passenger railcar");
        this.maxSeats = 54;
        this.maxLuggage = 25;
        this.comfortLevel = ComfortLevel.TOLERABLE;
    }

    public PassengerRailCar(String name, int maxSeats, double maxBaggage, ComfortLevel comfortLevel) {
        this.setName(name);
        this.maxSeats = maxSeats;
        this.maxLuggage = maxBaggage;
        this.comfortLevel = comfortLevel;
    }

    public PassengerRailCar(PassengerRailCar refRC) {
        this.setName(refRC.getName());
        this.maxSeats = refRC.maxSeats;
        this.maxLuggage = refRC.maxLuggage;
        this.comfortLevel = refRC.comfortLevel;
    }

    public void addPassenger(Passenger passenger) {

        if (!isOverloaded()) {
            placeLuggage(passenger);
            seatPassenger(passenger);
        }

    }

    void placeLuggage(Passenger passenger) {

        if ((passenger.getLuggage() != null) && (loadedLuggage.size() <= maxLuggage)) {
            loadedLuggage.put(passenger, passenger.getLuggage());
        }
    }

    void seatPassenger(Passenger passenger) {
        for (int i = 1; i < maxSeats; i++) {
            if (!seatedPassengers.containsKey(i)) {
                seatedPassengers.put(i, passenger);
                break;
            }
        }
    }

    public void leaveRailCar(Station station) {

        if ((station instanceof Depo) || (station instanceof TerminalCargoStation)) {
            seatedPassengers.clear();
            loadedLuggage.clear();
        } else {
            offloadPassengers((TrainStation) station);
        }

    }

    void offloadPassengers(TrainStation station) {

        String luggageName="";
        Passenger passRef=null;

        for (Integer seat : new ArrayList<Integer>(seatedPassengers.keySet())) {

            if (seatedPassengers.get(seat).getDestination() == station) {

                passRef=seatedPassengers.get(seat);

                logger.info(" Passenger "+passRef.getName()+
                        getLuggageName(passRef)+
                        " leaves train at "+station.getName()+" station.");

                seatedPassengers.remove(seat);
            }
        }

    }

    void offloadLuggage(Passenger passenger) {
        if (loadedLuggage.containsKey(passenger)) {
            loadedLuggage.remove(passenger);
        }
    }

    Cargo getLuggage(Passenger passenger) {
        Cargo luggageRef=null;
        if (loadedLuggage.containsKey(passenger)) {
            luggageRef=loadedLuggage.get(passenger);
        }
        return luggageRef;
    }

    String getLuggageName(Passenger passenger){
        if(getLuggage(passenger)!=null){
            return " carrying "+getLuggage(passenger).getName();
        }else{
            return "";
        }
    }

    public int countPassengers() {
        return seatedPassengers.size();
    }

    public int countLuggage() {
        return loadedLuggage.size();
    }

    @Override
    public double computeWeight() {
        double summaryWeight = 0;

        for (Map.Entry<Integer, Passenger> passenger : seatedPassengers.entrySet()) {
            summaryWeight += passenger.getValue().getWeight();
        }
        for (Map.Entry<Passenger, Cargo> luggage : loadedLuggage.entrySet()) {
            summaryWeight += luggage.getValue().getWeight();
        }
        return summaryWeight;
    }

    @Override
    public boolean isOverloaded() {
        return seatedPassengers.size() > maxSeats;
    }

    @Override
    public int compareTo(PassengerRailCar otherRailCart) {
        return this.comfortLevel.compareTo(otherRailCart.comfortLevel);
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    @Override
    public String toString() {
        Cargo refLuggage=null;

        String outputStr="RailCar "+getName()+" of comfort level "+comfortLevel.name()+" with max seats "+maxSeats+
                " can hold luggage " + maxLuggage +" currently has on board:";

        for (Integer seat : new ArrayList<Integer>(seatedPassengers.keySet())){
            outputStr+="\n"+seat+" "+seatedPassengers.get(seat).getName()+
                    getLuggageName(seatedPassengers.get(seat));
        }

        return outputStr;
    }

    public void getEverythingOff(){
        seatedPassengers.clear();
        loadedLuggage.clear();
    }

}
