package BattleConstructor.BattleArena;

import java.util.ArrayList;
import java.util.List;

import Droids.Droid;
import Droids.Parts.Part;

public class BaseArena {

    int maxSize;

   private List<Droid> defendingDroids = new ArrayList<Droid>();
   private List<Droid> attackingDroids = new ArrayList<Droid>();

    public BaseArena(int size) {
        maxSize = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void printAttackers(){
        for(Droid droid : attackingDroids){
            System.out.printf("\n"+droid.toString());
        }
    }

    public void printDefenders(){
        for(Droid droid : defendingDroids){
            System.out.printf("\n"+droid.toString());
        }
    }

    public boolean selectDefenders(Droid droid) {

        if (defendingDroids.size() < maxSize) {
            defendingDroids.add(droid);
            return true;
        }
        return false;
    }

    public boolean selectAttackers(Droid droid) {

        if (attackingDroids.size() < maxSize) {
            attackingDroids.add(droid);
            return true;
        }
        return false;
    }

    boolean ArmyAlive(List<Droid> Army) {

        if (Army.isEmpty()) {
            return false;
        }
        return true;
    }

    void armyAttack(List<Droid> attackingArmy, List<Droid> defendingArmy) {

        for (Droid droid : attackingArmy) {
            System.out.printf("\n"+droid.Type+" attacks!");
            droid.AttackTargetedPart(defendingArmy);
        }
    }

    void armyStatus(List<Droid> Army) {
        int ind = 0,toRemoval=0;
        boolean sbDied=false;
        List<Integer> RemoveList = new ArrayList<Integer>();
        List<Droid> toScrap = new ArrayList<Droid>();

        for (Droid droid : Army) {
            droid.UpdateIsDroidAlive();
            if(!droid.isAlive){
                toScrap.add(droid);
            }
        }

        for(Droid scrap:toScrap){
            Army.remove(scrap);
        }

    }

    void victoriousArmy(){

        if (ArmyAlive(attackingDroids)) {
            armyStatus(attackingDroids);
            System.out.printf("\n\nAttackers won.");
        } else {
            armyStatus(defendingDroids);
            System.out.printf("\n\nDefenders won.");
        }
    }

    void roundAnnouncements(int inc, boolean side){

        System.out.printf("\n\nRound:" + inc+"\n");
        if(side) {
            System.out.printf("Attackers turn!\n");
        }else{
            System.out.printf("Defenders turn!\n");
        }
    }

    public void startFight() {
        boolean attackersTurn = true, proceed = true;
        int inc = 0;

        while (proceed) {
            inc++;
            roundAnnouncements(inc,attackersTurn);
            if (attackersTurn) {
                armyAttack(attackingDroids, defendingDroids);
            } else {
                armyAttack(defendingDroids, attackingDroids);
            }
            attackersTurn = !attackersTurn;
            armyStatus(attackingDroids);
            armyStatus(defendingDroids);
            proceed = ArmyAlive(attackingDroids) && ArmyAlive(defendingDroids);
        }

        victoriousArmy();
    }

}
