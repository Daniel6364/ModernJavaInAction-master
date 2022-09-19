package modernjavainaction.chap06.daniel06;

import modernjavainaction.chap06.Dish;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DanielPrac06Test {

    @Test
    public void testSolution01() {

        Optional<Dish> expectResult = Optional.of(new Dish("pork", false, 800, Dish.Type.MEAT));

        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> result = Dish.menu.stream()
                                         .collect(Collectors.maxBy(dishComparator));

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expectResult.get().getName(), result.get().getName());
    }

    @Test
    public void testSolution02() {

        int expectResult = 4300;

        int result = Dish.menu.stream()
                              .collect(Collectors.summingInt(Dish::getCalories));

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);

    }


    @Test
    public void testSolution03() {

        IntSummaryStatistics expectResult = new IntSummaryStatistics(9, 120, 800, 4300);

        IntSummaryStatistics result = Dish.menu.stream()
                                               .collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);

        Double expectResultAverage = expectResult.getAverage();
        System.out.println(expectResultAverage);

        Double resultAverage = result.getAverage();
        System.out.println(resultAverage);

        Assert.assertEquals(expectResultAverage, resultAverage);

        long expectResultSum = expectResult.getSum();
        long resultSum = result.getSum();

        Assert.assertEquals(expectResultSum, resultSum);
    }


    @Test
    public void solution04Test() {

        String expectResult = "porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon";

        String result = Dish.menu.stream()
                                 .map(Dish::getName).collect(Collectors.joining());

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result       : " + result);

        String result2 = Dish.menu.stream()
                                  .map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("result2       : " + result2);

    }

    @Test
    public void solution05Test() {
        int result = Dish.menu.stream()
                              .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        int result2 = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        int result3 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();

        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
    }

    @Test
    public void solution07Test() {

        List<Dish> expectResultList = Arrays.asList(
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        Map<Dish.Type, List<Dish>> result = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));

        String expectResultName = expectResultList.get(0).getName();
        String resultName = result.get(Dish.Type.FISH).get(0).getName();

        System.out.println(expectResultName);
        System.out.println(resultName);

        Assert.assertEquals(expectResultName, resultName);

    }

    @Test
    public void solution08Test() {

        Map<DanielPrac06.CaloricLevel, List<Dish>> result
                = Dish.menu.stream()
                           .collect(Collectors.groupingBy(dish -> {
                               if (dish.getCalories() <= 400) {
                                   return DanielPrac06.CaloricLevel.DIET;
                               } else if (dish.getCalories() <= 700) {
                                   return DanielPrac06.CaloricLevel.NORMAL;
                               } else {
                                   return DanielPrac06.CaloricLevel.FAT;
                               }
                           }));
        System.out.println(result);

    }
}