package modernjavainaction.chap02.daniel02;

import scala.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApplesDaniel {

    //    strategy design pattern
    public interface ApplePredicate {
        boolean test(Apple apple);

    }

    public static class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public static class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equals(Color.GREEN);
        }
    }

    enum Color {
        GREEN, RED
    }

    public static class Apple {
        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" + "weight=" + weight + ", color=" + color + '}';
        }
    }


    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) result.add(apple);
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) result.add(apple);
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) result.add(apple);
        }
        return result;
    }


    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }


    public static List<Apple> filterApples2(List<Apple> inventory, ApplePredicate ap) {
        System.out.println("==// filterApples2");
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (ap.test(apple)) result.add(apple);
        }
        return result;
    }


    public static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }


    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(180, Color.GREEN), new Apple(120, Color.RED));


//        ==========// Worst Examples
        /*
        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        System.out.println("greenApples : " + greenApples);

        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println("redApples : " + redApples);

        List<Apple> heavyApples = filterApplesByWeight(inventory, 150);
        System.out.println("heavyApples : " + heavyApples);

        List<Apple> greenApples2 = filterApples(inventory, Color.GREEN, 0, true);
        System.out.println("greenApples2 : " + greenApples2);

        List<Apple> redApples2 = filterApples(inventory, Color.RED, 150, false);
        System.out.println("redApples2 : " + redApples2);
        */

        List<Apple> redAndHeavyApples = filterApples2(inventory, new AppleRedAndHeavyPredicate());
        System.out.println("redAndHeavyApples : " + redAndHeavyApples);

    }


}
