import org.junit.Test;

import java.util.function.Function;

/**
 * @ClassName TestArrayRef
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/
public class TestArrayRef {
    //数组引用
    //Type[]::new
    @Test
    public void test()
    {
        Function<Integer,String[]> fun = (x)->new String[x];
        String[] strings = fun.apply(10);
        System.out.println(strings.length);

        Function<Integer,String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);


    }
}
