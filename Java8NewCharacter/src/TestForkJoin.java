import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName TestForkJoin
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/20
 **/
public class TestForkJoin {

    @Test
    public void test()
    {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,100000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

    }

    /**
     * Java8并行流
     */
    @Test
    public void test1()
    {
        Instant start = Instant.now();
        //串行流
        LongStream.rangeClosed(0,100000000000L)
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("串行流耗费时间为："+ Duration.between(start,end).toMillis());


        start = Instant.now();
        //并行流
        LongStream.rangeClosed(0,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        end = Instant.now();
        System.out.println("并行流耗费时间为："+ Duration.between(start,end).toMillis());

    }
}
