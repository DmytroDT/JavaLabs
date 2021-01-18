package menu.command;

import menu.MainMenu;

import java.io.IOException;

public class Close extends Command{

    MainMenu menu;

    public Close(MainMenu menu){
        this.menu = menu;
    }

    @Override
    public void execute(String inputString) throws IOException, ClassNotFoundException {
        menu.stopExecution();
    }
}
