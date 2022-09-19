package modernjavainaction.chap06.daniel06;

import modernjavainaction.chap06.Dish;

import java.util.*;
import java.util.stream.Collectors;




public class DanielPrac06 {

    private Optional<Dish> solution01() {

        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);

         return Dish.menu.stream()
                        .collect(Collectors.maxBy(dishComparator));
    }

    private int solution02() {

        return Dish.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
    }

    private IntSummaryStatistics solution03() {

        return Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
    }

    private String solution04() {
        return Dish.menu.stream()
                .map(Dish::getName).collect(Collectors.joining());
    }

    private int solution05() {
        return Dish.menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
    }

    private int solution06() {
        return Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }


    private Map<Dish.Type, List<Dish>> solution07() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    public enum CaloricLevel { DIET, NORMAL, FAT }

    private void solution08() {

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));

    }


}
