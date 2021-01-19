package transportSystem.train.railcar;

import transportSystem.baggage.Cargo;

import java.io.Serializable;
import java.util.*;

public class CargoRailCar extends RailCar implements Serializable {

    double maxCapacity;
    List<Cargo> cargoList = new ArrayList<Cargo>();

    public CargoRailCar() {
        setName("standard cargo railcar");
        this.maxCapacity = 90;
        this.setCartWeight(25000);
        this.setCartMaxWeight(120000);
    }

    public CargoRailCar(double maxCapacity, List<Cargo> cargoList) {
        this.maxCapacity = maxCapacity;
        this.cargoList = cargoList;
    }

    public CargoRailCar(CargoRailCar refRC) {
        this.maxCapacity = refRC.maxCapacity;
        cargoList.addAll(refRC.cargoList) ;
    }

    Cargo summaryCargo() {
        double summaryVolume = 0;
        double summaryWeight = 0;

        for (Cargo cargo : cargoList) {
            summaryVolume += cargo.getVolume();
            summaryWeight += cargo.getWeight();
        }
        return new Cargo(summaryVolume, summaryWeight, "");
    }

    public boolean loadCargo(Cargo cargo) {

        if (!isOverloaded() ||
                (summaryCargo().getVolume() + cargo.getVolume()) > maxCapacity) {
            return false;
        }
//TODO: Loading existing cargo exception?
        cargoList.add(cargo);
        return true;
    }

    Cargo retrieveCargo(String name) {
        Cargo Reference = new Cargo();

        for (Cargo cargo : cargoList) {
            if (cargo.getName() == name) {
                Reference = cargo;
            }
        }
        cargoList.remove(Reference);
        return Reference;
    }

    @Override
    public double computeWeight() {
        return (this.getCartWeight() + summaryCargo().getWeight());
    }

    @Override
    public boolean isOverloaded() {
        return computeWeight() > this.getCartMaxWeight();
    }

    @Override
    public String toString() {
        return "CargoRailCar{" +
                "maxCapacity=" + maxCapacity +
                ", CargoList=" + cargoList +
                ", cartWeight=" + cartWeight +
                ", cartMaxWeight=" + cartMaxWeight +
                '}';
    }
}
