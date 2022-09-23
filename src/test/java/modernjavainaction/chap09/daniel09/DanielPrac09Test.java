package modernjavainaction.chap09.daniel09;

import org.junit.Test;

import static org.junit.Assert.*;

public class DanielPrac09Test {


    @Test
    public void solution01() {

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello r1");
            }
        };

        Runnable r2 = () -> System.out.println("Hello r2");

    }
}