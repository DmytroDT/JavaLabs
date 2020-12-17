package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class StartFight extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.startFight();
    }
}
