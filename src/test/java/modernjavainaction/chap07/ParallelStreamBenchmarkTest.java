package modernjavainaction.chap07;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmarkTest {

    private static final long N = 10_000_000L;


    @Test
    @Benchmark
    public void iterativeSum() {
        long result = 0;
        for (long i = 1L; i <= N; i++) {
            result += i;
        }

    }

    @Test
    @Benchmark
    public void parallelRangedSum() {
        long result = LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::sum);
    }

    @Test
    public void benchMark() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ParallelStreamBenchmarkTest.class.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(1)
//                .forks(1)
                .build();
        new Runner(options).run();

        /*
        * Benchmark                                      Mode  Cnt  Score   Error  Units
          ParallelStreamBenchmarkTest.iterativeSum       avgt       2.346          ms/op
          ParallelStreamBenchmarkTest.parallelRangedSum  avgt       0.277          ms/op
        * */
    }
}