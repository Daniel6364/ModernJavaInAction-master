package modernjavainaction.chap08.daniel08;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DanielPrac08 {

    private void Solution01() {

        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        long parallelismThreshold = 1;
        Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
        System.out.println(maxValue);

    }

    public static void main(String[] args) {
        DanielPrac08 main = new DanielPrac08();
        main.Solution01();
    }

}
