package modernjavainaction.chap06.daniel06;

import modernjavainaction.chap06.Dish;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


        Map<Dish.Type, List<Dish>> caloricDishesByType2
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(Dish::getType,
                                                          Collectors.filtering(
                                                                  dish -> dish.getCalories() > 500,
                                                                  Collectors.toList())));
        System.out.println(caloricDishesByType2);

        Map<Dish.Type, List<String>> dishNamesByType
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(Dish::getType,
                                                          Collectors.mapping(Dish::getName,
                                                                             Collectors.toList())));
        System.out.println(dishNamesByType);


        Map<Dish.Type, Set<String>> dishNamesByType2
                = Dish.menu.stream()
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

        Map<Dish.Type, Map<CaloricLever, List<Dish>>> dishesByTypeCaloricLevel
                = Dish.menu.stream()
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
        Map<Dish.Type, Long> typesCount
                = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(typesCount);


        Map<Dish.Type, Optional<Dish>> mostCaloricByType
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(Dish::getType,
                                                          Collectors.maxBy(
                                                                  Comparator.comparing(
                                                                          Dish::getCalories))));
        System.out.println(mostCaloricByType);

    }


    @Test
    public void Solution06Test() {
        Map<Dish.Type, Dish> mostCaloricByType
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(Dish::getType,
                                                          Collectors.collectingAndThen(
                                                                  Collectors.maxBy(
                                                                          Comparator.comparing(
                                                                                  Dish::getCalories)),
                                                                  Optional::get)));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByType2
                = Dish.menu.stream()
                           .collect(Collectors.toMap(Dish::getType, Function.identity(),
                                                     BinaryOperator.maxBy(Comparator.comparing(
                                                             Dish::getCalories))));
        System.out.println(mostCaloricByType2);

    }


    @Test
    public void Solution07Test() {
        Map<Dish.Type, Integer> totalCaloriesByType
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(Dish::getType,
                                                          Collectors.summingInt(
                                                                  Dish::getCalories)));
        System.out.println(totalCaloriesByType);
    }

    @Test
    public void Solution08Test() {
        Map<Dish.Type, Set<CaloricLever>> caloricLevelsByType
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                                   dish -> {
                                       if (dish.getCalories() <= 400) return CaloricLever.DIET;
                                       else if (dish.getCalories() <= 700) return CaloricLever.NORMAL;
                                       else return CaloricLever.FAT;
                                   }, Collectors.toSet()
                           )));
        System.out.println(caloricLevelsByType);

        Map<Dish.Type, Set<CaloricLever>> caloricLevelsByType2
                = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                dish -> {
                    if (dish.getCalories() <= 400) return CaloricLever.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLever.NORMAL;
                    else return CaloricLever.FAT;
                }, Collectors.toCollection(HashSet::new)
        )));
        System.out.println(caloricLevelsByType2);

    }

    @Test
    public void Solution09Test() {
        Map<Dish.Type, Set<CaloricLever>> caloricLevelsByType2
                = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                dish -> {
                    if (dish.getCalories() <= 400) return CaloricLever.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLever.NORMAL;
                    else return CaloricLever.FAT;
                }, Collectors.toCollection(HashSet::new)
        )));
        System.out.println(caloricLevelsByType2);
    }

    @Test
    public void Solution10Test() {
        Map<Boolean, List<Dish>> partitionedMenu
                = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes);

        List<Dish> vegetarianDishes2 = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(vegetarianDishes2);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType
                = Dish.menu.stream()
                           .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                                              Collectors.groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian
                = Dish.menu.stream()
                           .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                                              Collectors.collectingAndThen(
                                                                      Collectors.maxBy(
                                                                              Comparator.comparingInt(
                                                                                      Dish::getCalories)),
                                                                      Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);

    }

    @Test
    public void Quiz01Test() {

        Map<Boolean, Map<Boolean, List<Dish>>> result
                = Dish.menu.stream()
                           .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                                              Collectors.partitioningBy(
                                                                      dish -> dish.getCalories() > 500)));
        System.out.println(result);

    }

    @Test
    public void Quiz02Test() {
        Map<Boolean, Long> result
                = Dish.menu.stream()
                           .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
        System.out.println(result);
    }

    @Test
    public void Solution11Test() {

        /*6.4.2 숫자를 소수와 비소수롤 분할하기 - p222*/
        int candidate = 24;
        boolean result = IntStream.range(2, candidate).noneMatch(i -> i % candidate == 0);

        System.out.println(result);

        int candidateRoot = (int) Math.sqrt((double) candidate);
        System.out.println(candidateRoot);
        boolean result2 = IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);

        System.out.println(result2);

    }




}