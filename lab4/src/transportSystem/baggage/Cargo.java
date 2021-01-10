package transportSystem.baggage;

public class Cargo {
    double volume;
    double weight;
    String name;
    String deadline;

    public Cargo( ) {
    }

    public Cargo(double volume, double weight, String name, String deadline) {
        this.volume = volume;
        this.weight = weight;
        this.name = name;
        this.deadline = deadline;
    }

    public Cargo(double volume, double weight, String name) {
        this.volume = volume;
        this.weight = weight;
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
