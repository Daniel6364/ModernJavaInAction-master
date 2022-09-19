package modernjavainaction.chap06.daniel06;

import akka.stream.impl.fusing.Collect;
import modernjavainaction.chap06.Dish;
import modernjavainaction.chap06.Grouping;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DanielPrac06Test {

    enum CaloricLever {DIET, NORMAL, FAT}

    ;

    @Test
    public void Solution01Test() {

        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));

        System.out.println(dishesByType);
    }


    @Test
    public void Solution02Test() {
        Map<CaloricLever, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLever.DIET;
            else if (dish.getCalories() <= 700) return CaloricLever.NORMAL;
            else return CaloricLever.FAT;
        }));

        System.out.println(dishesByCaloricLevel);
    }


    @Test
    public void Solution03Test() {
        Map<Dish.Type, List<Dish>> caloricDishesByType = Dish.menu.stream()
                                                                  .filter(dish -> dish.getCalories() > 500)
                                                                  .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(caloricDishesByType);


        Map<Dish.Type, List<Dish>> caloricDishesByType2 = Dish.menu.stream()
                                                                   .collect(Collectors.groupingBy(Dish::getType,
                                                                                                  Collectors.filtering(
                                                                                                          dish -> dish.getCalories() > 500,
                                                                                                          Collectors.toList())));
        System.out.println(caloricDishesByType2);

        Map<Dish.Type, List<String>> dishNamesByType = Dish.menu.stream()
                                                                .collect(Collectors.groupingBy(Dish::getType,
                                                                                               Collectors.mapping(Dish::getName,
                                                                                                                  Collectors.toList())));
        System.out.println(dishNamesByType);


        Map<Dish.Type, Set<String>> dishNamesByType2 = Dish.menu.stream()
                                                                .collect(Collectors.groupingBy(Dish::getType,
                                                                                               Collectors.flatMapping(
                                                                                                       dish -> Dish.dishTags.get(
                                                                                                                           dish.getName())
                                                                                                                            .stream(),
                                                                                                       Collectors.toSet())));
        System.out.println(dishNamesByType2);


    }

    @Test
    public void Solution04Test() {

        Map<Dish.Type, Map<CaloricLever, List<Dish>>> dishesByTypeCaloricLevel = Dish.menu.stream()
                                                                                          .collect(Collectors.groupingBy(
                                                                                                  Dish::getType,
                                                                                                  Collectors.groupingBy(dish -> {
                                                                                                      if (dish.getCalories() <= 400)
                                                                                                          return CaloricLever.DIET;
                                                                                                      else if (dish.getCalories() < 700)
                                                                                                          return CaloricLever.NORMAL;
                                                                                                      else
                                                                                                          return CaloricLever.FAT;
                                                                                                  })));
        System.out.println(dishesByTypeCaloricLevel);

    }


    @Test
    public void Solution05Test() {
        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(typesCount);


        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream()
                                                                    .collect(Collectors.groupingBy(Dish::getType,
                                                                                                   Collectors.maxBy(
                                                                                                           Comparator.comparing(
                                                                                                                   Dish::getCalories))));
        System.out.println(mostCaloricByType);

    }


    @Test
    public void Solution06Test() {
        Map<Dish.Type, Dish> mostCaloricByType = Dish.menu.stream()
                                                          .collect(Collectors.groupingBy(Dish::getType,
                                                                                         Collectors.collectingAndThen(
                                                                                                 Collectors.maxBy(
                                                                                                         Comparator.comparing(
                                                                                                                 Dish::getCalories)),
                                                                                                 Optional::get)));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByType2 = Dish.menu.stream()
                                                           .collect(Collectors.toMap(Dish::getType, Function.identity(),
                                                                                     BinaryOperator.maxBy(Comparator.comparing(
                                                                                             Dish::getCalories))));
        System.out.println(mostCaloricByType2);

    }


    @Test
    public void Solution07Test() {
        Map<Dish.Type, Integer> totalCaloriesByType = Dish.menu.stream()
                                                               .collect(Collectors.groupingBy(Dish::getType,
                                                                                              Collectors.summingInt(
                                                                                                      Dish::getCalories)));
        System.out.println(totalCaloriesByType);
    }
}