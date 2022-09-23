package modernjavainaction.chap09.daniel09;

public class DanielPrac09 {

    private void solution01() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello r1");
            }
        };

        Runnable r2 = () -> System.out.println("Hello r2");

    }


    public static void main(String[] args) {
//        new DanielPrac09().solution01();

        FeedTest f = new FeedTest();
//        f.registerObserverTest(new ChicagoTimes());
//        f.notifyObserverTests("The Chicago cubs said they want to be won the game this season");


        FeedTest f2 = new FeedTest();
        f2.registerObserverTest((String tweet) -> {
            if (tweet != null && tweet.contains("cubs")) {
                System.out.println("Rigley Field reported that " + tweet);
            }
        });
        f2.notifyObserverTests("cubs");

    }

}
