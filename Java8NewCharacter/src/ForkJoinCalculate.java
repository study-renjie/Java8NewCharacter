import java.util.concurrent.RecursiveTask;

/**
 * @ClassName ForkJoinCalculate
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/20
 **/
//RecursiveTask有返回值
//RecursiveAcation没有返回值
//Recursive是递归的意思
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long len = end -start;
        if(len<=THRESHOLD)
        {
            long sum = 0;
            for(long i = start;i<=end;i++)
            {
                sum+=i;
            }
            return sum;
        }
        else
        {
            long middle = (start+end)/2;
            ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
            left.fork();//拆分子任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle+1,end);
            right.fork();
            return left.join()+right.join();
        }

    }
}
