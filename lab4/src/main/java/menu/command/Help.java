package menu.command;

public class Help extends Command {

    @Override
    public void execute(String inputString) {
        System.out.printf("\nCommand formats:"
                + "\ndrc - displays railCars"
                + "\nds - displays stations"
                + "\ndt - displays trains"
                + "\ndrcts - display railcars, trains , stations"
                + "\ndrcbc - display railcars by comfort level: train index"
                + "\ndrcpc - display railcar with specified passenger count : train index, lower bound, upper bound"
                + "\ndatp - display awaiting train passengers  : station index"
                + "\nplc - display passenger and luggage count of a train : train index"
                + "\nfwrp -fills stations with randomly distributed passengers : passenger count "
                + "\nct - create train : name "
                + "\narc - add railcar to train: train index + railcar indexes"
                + "\nas - add station to train route: train index + station indexes"
                + "\ndeltrn - delete train : train index"
                + "\npt - progress time : hours"
                + "\nsa - save all "
                + "\nla - load all"
                + "\nloi - legacy old init"
                + "\nexit - exit program"
        );
    }
}
