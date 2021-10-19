package menu.Commands;

import menu.Command;

public class Help extends Command {

    @Override
    public void execute(String inputString) {
        System.out.printf("\nCommand formats:"
                +"\nad - Assemble a droid from available parts -  input type of droid & sequence of part indexes to create droid from."
                +"\ncrp - Create part - input parts name, base health, damage, range, efficiency on breakdown  - [s,d,d,i,d]. "
                +"\nia - Initialize arena - set default parameters for arena."
                +"\nra - Reinitialize arena - input max number of Droids in each team."
                +"\npa - Pick attacking droids - input series of droid indexes to add to attackers from droid list."
                +"\npd - Pick defending droids - input series of droid indexes to add to defenders from droid list."
                +"\nptd - Print assembled droids."
                +"\nptp - Print available parts."
                +"\nptt - Print composition of teams."
                +"\nsf - Start fight among droids."
        );
    }
}
