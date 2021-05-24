import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName TestDateFormatThreadLocal
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/22
 **/
public class TestDateFormatThreadLocal {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return DateFormatThreadLocal.convert("20161218");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> result = new ArrayList<>();
        for (int i = 0 ;i<10;i++) {

            result.add(pool.submit(task));
        }

        for(Future<Date> future:result)
        {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
