package modernjavainaction.chap06.daniel06;

import modernjavainaction.chap06.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DanielPrac06 {

    private void Solution01() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
    }


    public boolean isPrime(int candidate) {
        // 1
//        return IntStream.range(2, candidate).noneMatch(i -> i % candidate == 0);

        // 2
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);


    }



}
