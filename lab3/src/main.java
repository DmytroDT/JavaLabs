import BattleConstructor.BattleConstructor;
import menu.Command;
import menu.Commands.*;
import menu.Commands.CommandError;

import java.util.*;

public class main {
//TODO menu



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String passedString="";
        String[] comStr,chkStr;
        CommandError commandError = new CommandError();
        BattleConstructor Bc = new BattleConstructor();
        Bc.initialize();
        Map<String, Command> Menu = new HashMap<String, Command>();


        Menu.put("ad",new CreateDroid());
        Menu.put("crp",new CreatePart());
        Menu.put("ia",new InitializeArena());
        Menu.put("ra",new ReInitArena());
        Menu.put("pa",new PickAttackers());
        Menu.put("pd",new PickDefenders());
        Menu.put("ptd",new PrintDroids());
        Menu.put("ptp",new PrintParts());
        Menu.put("ptt",new PrintTeams());
        Menu.put("sf",new StartFight());
        Menu.put("help", new Help());

        System.out.printf(" Type in \"help\" to get list of commands.");

          while (true){
              System.out.printf("\n");
              passedString=sc.nextLine();
              chkStr = passedString.split(" ");

              if (chkStr.length<2){

                  Menu.getOrDefault(chkStr[0],commandError).execute("");

              }else{

                  comStr = passedString.split(" ",2);
                  Menu.getOrDefault(comStr[0],commandError).execute(comStr[1]);
              }

              if(chkStr[0]=="exit"){
                  break;
              }
          }



    }
}
