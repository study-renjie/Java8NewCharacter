import org.junit.Test;

/**
 * @ClassName TestLambda2
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/
public class TestLambda2 {
    //需求：对一个数进行运算
    @Test
    public void test()
    {
        Integer num = operation(100, x -> x * x);
        System.out.println(num);
    }
    public Integer operation(Integer num,MyFun mf)
    {
        return mf.getValue(num);
    }


}
