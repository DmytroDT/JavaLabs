public class Main {

    public static void main(String[] args) {
        GlobalTrainSystem gb = new GlobalTrainSystem();
        gb.initialize();
        gb.fillWithRandomPassengers(25);

        gb.printTrains();

        gb.progressTime(3);

        System.out.printf("Cart with less than 10 and more than 4  passengers :\n"+
                gb.trains.get(0).seekCarByPassengerNumbers(4,10).toString());

        System.out.printf("\n\n\nCarts by comfort: "+gb.displayRailCarsByComfort(0));
    }
}
