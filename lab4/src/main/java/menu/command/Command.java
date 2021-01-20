package menu.command;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(String inputString) throws IOException, ClassNotFoundException;

    protected boolean checkIncorrectCommandLength(String input, int argumentsCount) {
        String[] splittedString = input.split(" ");
        return splittedString.length != argumentsCount;
    }

    protected Integer tryParse(String input) {
        Integer retInt=0;
        input.trim();

        try{
            retInt=Integer.parseInt(input);
        }catch (NumberFormatException e){
            retInt=null;
        }
        return retInt;
    }

    protected String[] split(String input) {
        input.trim();
        String[] splittedString = input.split(" ");
        return splittedString;
    }
}
