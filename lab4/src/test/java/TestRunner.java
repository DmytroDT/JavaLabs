import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.model.TestClass;


public class TestRunner {

    public static void main(String[] args) {
        Result res = JUnitCore.runClasses(TrainTest.class);

        if(res.wasSuccessful()){
            System.out.printf("Train arrived at destination");
        }else{
            System.out.printf("Train failed to arrive");
        }
    }

}
