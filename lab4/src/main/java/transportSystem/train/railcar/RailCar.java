package transportSystem.train.railcar;

import java.io.Serializable;

public abstract class RailCar implements Serializable {

    protected String Name;
    protected double cartWeight;
    protected double cartMaxWeight;

    public abstract double computeWeight();

    public abstract boolean isOverloaded();

    protected double getCartWeight() {
        return cartWeight;
    }

    protected void setCartWeight(double cartWeight) {
        this.cartWeight = cartWeight;
    }

    public double getCartMaxWeight() {
        return cartMaxWeight;
    }

    public void setCartMaxWeight(double cartMaxWeight) {
        this.cartMaxWeight = cartMaxWeight;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
