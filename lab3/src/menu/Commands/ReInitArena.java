package menu.Commands;

import BattleConstructor.BattleConstructor;
import menu.Command;

public class ReInitArena extends Command {
    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.ResizeArena(Integer.parseInt(inputString));
    }
}
