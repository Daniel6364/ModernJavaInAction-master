package modernjavainaction.chap09.daniel09;

import java.util.ArrayList;
import java.util.List;

public class FeedTest implements SubjectTest{

    private final List<ObserverTest> observerTests = new ArrayList<>();

    @Override
    public void registerObserverTest(ObserverTest o) {
        observerTests.add(o);
    }

    @Override
    public void notifyObserverTests(String tweet) {
        observerTests.forEach(o -> o.inform(tweet));
    }
}
