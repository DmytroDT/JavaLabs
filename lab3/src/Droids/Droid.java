package Droids;

import Droids.Parts.Part;

import java.util.*;

public class Droid {

    static Random rand = new Random();
    Map<String, Part> ActiveDroidParts = new TreeMap<String, Part>();

    public String Type = "Melee";
    public boolean isAlive = true;

    public Droid() {
        initParts();
    }

    public Droid(String type) {
        this.Type = type;
        initParts();
    }

    public Droid(String type, Part head, Part torso, Part arms, Part legs) {
        Type = type;
        ActiveDroidParts.put(head.getPartName(), head);
        ActiveDroidParts.put(torso.getPartName(), torso);
        ActiveDroidParts.put(arms.getPartName(), arms);
        ActiveDroidParts.put(legs.getPartName(), legs);
    }

    public Droid(String type, Map<String,Part> partList) {
        Type = type;
        ActiveDroidParts.putAll(partList);
    }

    void initParts() {
        Part Head = new Part("Head", 10, 1, 0, 0.9);
        Part Torso = new Part("Torso", 100, 5, 0, 0.5);
        Part Arms = new Part("Arms", 20, 4, 0, 0.8);
        Part Legs = new Part("Legs", 30, 5, 0, 0.8);

        ActiveDroidParts.put(Head.getPartName(), Head);
        ActiveDroidParts.put(Torso.getPartName(), Torso);
        ActiveDroidParts.put(Arms.getPartName(), Arms);
        ActiveDroidParts.put(Legs.getPartName(), Legs);
    }

    public String[] getPartsList() {
        String[] parts = ActiveDroidParts.keySet().toArray(new String[ActiveDroidParts.size()]);
        return parts;
    }

    int getMaxRange(Map<String, Part> partsList) {
        int farthestRange = 0, range;
        for (Map.Entry<String, Part> part : ActiveDroidParts.entrySet()) {
            range = part.getValue().getRange();
            if (range > farthestRange) {
                farthestRange = range;
            }
        }
        return farthestRange;
    }

    String getMaxDmg(Map<String, Part> partsList, int farthestRange) {
        double highestDamage = 0, damage;
        int range;
        String chosenPart = "";

        for (Map.Entry<String, Part> part : ActiveDroidParts.entrySet()) {

            range = part.getValue().getRange();
            damage = part.getValue().getDamageResource();

            if (range == farthestRange) {
                if (highestDamage < damage) {
                    highestDamage = damage;
                    chosenPart = part.getValue().getPartName();
                }
            }

        }
        return chosenPart;
    }

    String chooseAttackingPart() {
        int range;
        String maxRDpart = "";

        if (!ActiveDroidParts.isEmpty()) {
            range = getMaxRange(ActiveDroidParts);
            maxRDpart = getMaxDmg(ActiveDroidParts, range);
        }

        return maxRDpart;
    }

    public boolean TakeDamageTo(String PartName, double damage) {

        if (!ActiveDroidParts.isEmpty()) {
            ActiveDroidParts.get(PartName).takeDamage(damage);
            System.out.printf("\nAndroid " + Type + " took " + damage + " in part " + PartName + "\nRemaining part health: " + ActiveDroidParts.get(PartName).getPartHealth());
            return true;
        }
        return false;
    }

    public boolean UpdateIsDroidAlive() {
        int inc = 0,destroyedIndex;
        List<String>   destroyedParts= new ArrayList<String>();

        for (Map.Entry<String, Part> part : ActiveDroidParts.entrySet()) {
            if (part.getValue().isDestroyed()) {
                destroyedParts.add((part.getKey()));
            }
        }

        for(String partName:destroyedParts){
            ActiveDroidParts.remove(partName);
        }

        if ( ActiveDroidParts.size() - 1<2) {
            isAlive = false;
            Type="dead";
        }
        if (ActiveDroidParts.get("Torso").isDestroyed()) {
            isAlive = false;
        }

        return isAlive;
    }

    @Override
    public String toString() {
        String PartStatus = "";
        for (Map.Entry<String, Part> part : ActiveDroidParts.entrySet()) {
            PartStatus += "\n" + part.getValue().partStatus();
        }
        return "\nDroid of type " + Type + " is alive -" + isAlive + PartStatus;
    }

    public void AttackTargetedPart(List<Droid> positions) {

        String chosenPart = chooseAttackingPart();
        Part partReference = ActiveDroidParts.get(chosenPart);
        int range = partReference.getRange();

        while(range>positions.size()-1){
            range--;
        }

        if (positions.get(range) != null) {
            Droid defendingReference = positions.get(range);
            String[] partsList = defendingReference.getPartsList();
            int randomPart = rand.nextInt(partsList.length);
            defendingReference.TakeDamageTo(partsList[randomPart], partReference.getDamageResource());
        }
    }

}
