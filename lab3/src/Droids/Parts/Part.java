package Droids.Parts;

public class Part {

    String partName = "Part";
    double partHealth = 100;
    double healthThreshold = partHealth * 0.3;
    double damageResource = 1;
    double disableCoefficient = 0.6;
    int range = 0;

    public Part() {
    }

    public Part(String partName, double partHealth, double damageResource,int range, double disableCoefficient) {
        this.partName = partName;
        this.partHealth = partHealth;
        this.damageResource = damageResource;
        this.range=range;
        this.disableCoefficient = disableCoefficient;
        healthThreshold = partHealth * 0.3;
    }

    public String getPartName() {
        return partName;
    }

    public double getPartHealth() {
        return partHealth;
    }

    public double getDamageResource() {
        return damageResource;
    }

    public int getRange() { return range; }

    public Boolean isOperational() {
        Boolean status = true;
        if (partHealth < healthThreshold) {
            status = false;
        }
        return status;
    }

    public Boolean isDestroyed() {
        Boolean status = false;
        if (partHealth <= 0) {
            status = true;
        }
        return status;
    }

    public void takeDamage(double damage) {
        partHealth -= damage;
        if (!isOperational()) {
            damageResource -= damageResource * disableCoefficient;
            range-=range*disableCoefficient;
        } else {
            if (isDestroyed()) {
                damageResource = 0;
                range=0;
            }
        }
    }

    public String partStatus() {
        String statusMessage = partName + " is ";
        if (isOperational()) {
            statusMessage += " operational.";
        } else {
            if (!isDestroyed()) {
                statusMessage += " not operational.";
            } else {
                statusMessage += " destroyed.";
            }
        }
        return statusMessage;
    }

    public double inflictDamage() {
        return damageResource;
    }

    @Override
    public String toString() {
        return  partName +" has " + partHealth +" health, capable of dealing "
                + damageResource +" damage, works from range "+ range +", loses "
                + disableCoefficient*100 + "%% of efficiency when broken." ;
    }
}
