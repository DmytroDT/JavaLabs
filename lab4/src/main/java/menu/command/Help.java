package menu.command;

public class Help extends Command {

    @Override
    public void execute(String inputString) {
        System.out.printf("\nCommand formats:"
                + "\ndrc - displays railCars"
                + "\nds - displays stations"
                + "\ndt - displays trains"
                + "\ndrcts - display railcars, trains , stations"
                + "\ndrcbc - display railcars by comfort level -[i]: train index"
                + "\ndrcpc - display railcar with specified passenger count -[i,i,i]: train index, lower bound, upper bound"
                + "\ndatp - display awaiting train passengers"
                + "\nplc - display passenger and luggage count of a train -[i]: train index"
                + "\nfwrp -fills stations with randomly distributed passengers - [i]: passenger count "
                + "\nct - create train - [s]: name "
                + "\ncs - create station - [s] : name "
                + "\ncprc - create passenger railcar - [s,i,i,i]: name, max seats, max baggage containers, 0 < comfort level < 1000 "
                + "\narc - add railcar to train -[i,...,i]: train index, railcar indexes"
                + "\nas - add station to train route -[i,..,i]: train index, station indexes"
                + "\ndeltrn - delete train -[i]: train index"
                + "\npt - progress time -[i]: hours"
                + "\nsa - save all "
                + "\nla - load all"
                + "\nloi - legacy old init"
                + "\nclose - close program"
        );
    }
}
