package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class PickDefenders extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.pickDefendingDroids(inputString);
    }
}
