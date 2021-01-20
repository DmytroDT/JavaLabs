package menu;

import menu.command.*;
import org.apache.log4j.Logger;
import transportSystem.GlobalTrainSystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {

    final static Logger logger = Logger.getLogger(MainMenu.class);

    Scanner sc = new Scanner(System.in);
    GlobalTrainSystem gb;
    Map<String, Command> Menu = new HashMap<String, Command>();
    boolean continueCondition = true;

    public MainMenu(GlobalTrainSystem externalReference) throws IOException, ClassNotFoundException {

        gb = externalReference;

        Menu.put("close", new Close(this));
        Menu.put("help", new Help());

        Menu.put("la", new LoadAll(gb));
        Menu.put("sa", new SaveAll(gb));
        Menu.put("loi", new LegacyOldInit(gb));

        Menu.put("drc", new DisplayRailCarts(gb));
        Menu.put("ds", new DisplayStations(gb));
        Menu.put("dt", new DisplayTrains(gb));

        Menu.put("drcts", new DisplayRCsTsSts(gb));
        Menu.put("drcbc", new DisplayRCsByComfort(gb));
        Menu.put("drcpc", new DisplayRCsByPassengerCount(gb));
        Menu.put("datp", new DisplayAwaitingTrainPassengers(gb));
        Menu.put("plc", new PassengersAndLuggageCount(gb));

        Menu.put("pt", new ProgressTime(gb));
        Menu.put("fwrp", new FillWithRandomPassengers(gb));

        Menu.put("ct", new CreateTrain(gb));
        Menu.put("arc", new AddRailCar(gb));
        Menu.put("as", new AddStation(gb));
        Menu.put("deltrn", new DeleteTrain(gb));


        System.out.printf("Type in \"help\" to get list of commands.");
        commandsLoop();
    }

    void commandsLoop() throws IOException, ClassNotFoundException {

        String passedString = "";
        CommandError commandError = new CommandError();
        String[] chkStr;

        while (continueCondition) {
            System.out.printf("\n");

            passedString = sc.nextLine();
            passedString.trim();
            chkStr = passedString.split(" ", 2);

            //TODO implement better way of executing 1 word commands / add exception ?

            if ((chkStr.length >= 2) && (chkStr[1] != "")) {

                Menu.getOrDefault(chkStr[0], commandError).execute(chkStr[1]);

            } else {
                        Menu.getOrDefault(chkStr[0], commandError).execute("");
            }

        }
    }

    public void stopExecution() {
        continueCondition = false;
    }
}


