package modernjavainaction.chap06.daniel06;

import modernjavainaction.chap06.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz06 {


    public void Quiz01() {
        Map<Boolean, Map<Boolean, List<Dish>>> result
                = Dish.menu.stream()
                           .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                                              Collectors.partitioningBy(
                                                                      dish -> dish.getCalories() > 500)));
    }

    /*public void Quiz02() {
        Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                                                   Collectors.partitioningBy(Dish::getType)));
    }*/

    public void Quiz03() {
        Map<Boolean, Long> result = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
    }

}
