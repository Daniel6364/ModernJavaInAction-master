package modernjavainaction.chap07;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ParallelStreamsTest {

    public final int n = 8;

    @Test
    public void iterativeSum() {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        System.out.println(result);
        Assert.assertEquals(result, 36);


    }

    @Test
    public void parallelSum() {
        long result = Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
        System.out.println(result);
        Assert.assertEquals(result, 36);
    }

}