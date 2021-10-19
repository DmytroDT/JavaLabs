package menu.Commands;


import BattleConstructor.BattleConstructor;
import menu.Command;

public class CreatePart extends Command {

    BattleConstructor Bc = new BattleConstructor();

    @Override
    public void execute(String inputString) {
        Bc.createPart(inputString);
    }
}
