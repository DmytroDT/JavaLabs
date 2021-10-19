package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class PrintParts extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.printParts();
    }
}
