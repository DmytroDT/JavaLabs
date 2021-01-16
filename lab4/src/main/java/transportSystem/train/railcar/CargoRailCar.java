package transportSystem.train.railcar;

import transportSystem.baggage.Cargo;

import java.util.*;

public class CargoRailCar extends RailCar {

    double maxCapacity;
    List<Cargo> CargoList = new ArrayList<Cargo>();

    public CargoRailCar() {
        setName("standard cargo railcar");
        this.maxCapacity = 90;
        this.setCartWeight(25000);
        this.setCartMaxWeight(120000);
    }

    Cargo summaryCargo() {
        double summaryVolume = 0;
        double summaryWeight = 0;

        for (Cargo cargo : CargoList) {
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
        CargoList.add(cargo);
        return true;
    }

    Cargo retrieveCargo(String name) {
        Cargo Reference = new Cargo();

        for (Cargo cargo : CargoList) {
            if (cargo.getName() == name) {
                Reference = cargo;
            }
        }
        CargoList.remove(Reference);
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
                ", CargoList=" + CargoList +
                ", cartWeight=" + cartWeight +
                ", cartMaxWeight=" + cartMaxWeight +
                '}';
    }
}
