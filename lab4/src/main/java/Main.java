import menu.MainMenu;
import org.apache.log4j.Logger;
import transportSystem.GlobalTrainSystem;


import java.io.IOException;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        GlobalTrainSystem gb = new GlobalTrainSystem();

        try{
            MainMenu menu = new MainMenu(gb);
        }catch (Exception e){
            logger.error("program encountered error:",e);
        }

    }
}
