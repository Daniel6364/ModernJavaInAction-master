package modernjavainaction.chap09.daniel09;

public class ChicagoTimes implements ObserverTest{

    @Override
    public void inform(String tweet) {
        if (tweet != null && tweet.contains("Chicago")) {
            System.out.println("Breaking news in Chicago!" + tweet);
        }
    }
}
