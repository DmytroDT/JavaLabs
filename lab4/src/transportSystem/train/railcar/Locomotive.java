package transportSystem.train.railcar;

import transportSystem.train.Train;

public class Locomotive extends RailCar {

    double carryingPower;
    Train trainReference;

    public Locomotive(Train trainReference) {
        this.trainReference = trainReference;
        this.setCartWeight(144000);
        this.setCartMaxWeight(2000000);
        carryingPower=5000000;
    }

    @Override
    public double computeWeight() {
        return trainReference.getTrainWeight() + this.getCartWeight();
    }

    @Override
    public boolean isOverloaded() {
        return computeWeight() > this.getCartMaxWeight();
    }

}
