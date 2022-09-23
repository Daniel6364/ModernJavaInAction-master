package modernjavainaction.chap08.daniel08;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Quiz08 {

    public void Solution01() {
        Map<String, Integer> movies = new HashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);
        Iterator<Map.Entry<String, Integer>> iterator = movies.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) iterator.remove();
        }

        System.out.println(movies);
    }

    public void Solution02() {
        Map<String, Integer> movies = new HashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);

        movies.entrySet().removeIf(entry -> entry.getValue() < 10);

        System.out.println(movies);
    }



    public static void main(String[] args) {
        Quiz08 quiz08 = new Quiz08();
//        quiz08.Solution01();
        quiz08.Solution02();
    }

}
