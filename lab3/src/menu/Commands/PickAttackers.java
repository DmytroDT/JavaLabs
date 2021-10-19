package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class PickAttackers extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.pickAttackingDroids(inputString);
    }
}
