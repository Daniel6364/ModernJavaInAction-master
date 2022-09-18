package modernjavainaction.chap06.daniel06;

import modernjavainaction.chap06.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DanielPrac06 {

    private void Solution01() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
    }



}
