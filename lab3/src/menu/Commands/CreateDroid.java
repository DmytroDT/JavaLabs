package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class CreateDroid extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.createDroid(inputString);
    }
}
