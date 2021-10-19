package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class PrintDroids extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.printDroids();
    }
}
