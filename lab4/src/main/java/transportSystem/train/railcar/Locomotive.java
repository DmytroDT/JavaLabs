package transportSystem.train.railcar;

import transportSystem.train.Train;

public class Locomotive extends RailCar {

    double carryingPower;
    Train trainReference;

    public Locomotive() {
        setName("standard locomotive");
        this.setCartWeight(144000);
        this.setCartMaxWeight(2000000);
        carryingPower = 5000000;
    }

    public Locomotive(double cartWeight, double cartMaxWeight, double carryingPower, Train trainReference) {
        this.setCartWeight(cartWeight);
        this.setCartMaxWeight(cartMaxWeight);
        this.carryingPower = carryingPower;
        this.trainReference = trainReference;
    }

    public void setTrainReference(Train trainReference) {
        this.trainReference = trainReference;
    }

    @Override
    public double computeWeight() {
        return trainReference.getTrainWeight() + this.getCartWeight();
    }

    @Override
    public boolean isOverloaded() {
        return computeWeight() > this.getCartMaxWeight();
    }

    @Override
    public String toString() {
        return getName() +
                "carryingPower=" + carryingPower +
                ", cartWeight=" + cartWeight +
                ", cartMaxWeight=" + cartMaxWeight +
                '}';
    }

}