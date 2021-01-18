import menu.MainMenu;
import transportSystem.GlobalTrainSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        GlobalTrainSystem gb = new GlobalTrainSystem();
        MainMenu menu = new MainMenu(gb);
    }
}
