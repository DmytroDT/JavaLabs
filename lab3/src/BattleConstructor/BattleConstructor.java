package BattleConstructor;

import BattleConstructor.BattleArena.BaseArena;
import Droids.Droid;
import Droids.Parts.Part;
import java.util.*;


public  class BattleConstructor {

    private static List<Part> createdParts = new ArrayList<>();
    private static List<Droid> createdDroids = new ArrayList<>();
    private static BaseArena arenaReference;


    public void initialize(){
        fillDefaultParts();
        arenaReference = new BaseArena(1);
        createdDroids.add(new Droid());
    }

    public void fillDefaultParts(){
        Part Head = new Part("Head", 10, 1, 0, 0.9);
        Part Torso = new Part("Torso", 100, 5, 0, 0.5);
        Part Arms = new Part("Arms", 20, 4, 0, 0.8);
        Part Legs = new Part("Legs", 30, 5, 0, 0.8);

        createdParts.add(Head);
        createdParts.add(Torso);
        createdParts.add(Arms);
        createdParts.add(Legs);

    }

    double pd(String s){
        return Double.parseDouble(s);
    }

    public void createPart(String inputString){
        String[] s = inputString.split(" ");
        Part part = new Part(s[0],pd(s[1]),pd(s[2]),Integer.parseInt(s[3]),pd(s[4]));
        createdParts.add(part);

    }

    public void createDroid(String inputString){
        Map<String, Part> localParts = new TreeMap<String, Part>();
        String[] sequenceOfParts = inputString.split(" ");

        int index;
        Part partRef;

        for(int i =1;i<sequenceOfParts.length;i++){
            index=Integer.parseInt(sequenceOfParts[i]);
            partRef = createdParts.get(index);
            localParts.put(partRef.getPartName(),partRef);
            }

        Droid droid = new Droid(sequenceOfParts[0],localParts);
        createdDroids.add(droid);

        }

    public void ResizeArena(int size){
        arenaReference= new BaseArena(size);
    }

    public  void pickAttackingDroids(String inputString){
        String[] sequenceOfDroids = inputString.split(" ");
        Droid droidRef;

        for(int i =0;i<sequenceOfDroids.length;i++){
            droidRef=createdDroids.get(Integer.parseInt(sequenceOfDroids[i]));
            if(!arenaReference.selectAttackers(droidRef)){
                System.out.printf("Exceeded maximum amount of allowed droids");
            }
        }
    }

    public void pickDefendingDroids(String inputString){
        String[] sequenceOfDroids = inputString.split(" ");
        Droid droidRef;

        for(int i =0;i<sequenceOfDroids.length;i++){
            droidRef=createdDroids.get(Integer.parseInt(sequenceOfDroids[i]));
            if(!arenaReference.selectDefenders(droidRef)){
                System.out.printf("Exceeded maximum amount of allowed droids");
            }
        }
    }

    public void printParts(){
        for(int i =0 ;i<createdParts.size();i++){
            System.out.printf("\n"+i+". "+createdParts.get(i).toString());
        }
    }

    public  void printDroids(){
        for(int i =0 ;i<createdDroids.size();i++){
            System.out.printf("\n"+i+". "+createdDroids.get(i).toString());
        }
    }

    public void printTeams(){
        System.out.printf("\n\nAttacking army:");
        arenaReference.printAttackers();
        System.out.printf("\n\nDefending army:");
        arenaReference.printDefenders();
    }

    public void startFight(){
        arenaReference.startFight();
    }

}
