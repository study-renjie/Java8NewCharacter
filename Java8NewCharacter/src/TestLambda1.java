import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @ClassName TestLambda1
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/2
 **/
public class TestLambda1 {


    /**
     * 语法格式一：无参数并且无返回值
     */
    @Test
    public void test1()
    {
        //匿名内部类
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!");
            }
        };
        r.run();
        System.out.println("-------------");

        //Lambda表达式
        Runnable r1 = ()-> System.out.println("Hello Lambda");
        r1.run();
    }

    /**
     * 语法格式二：有一个参数，但是没有返回值。
     * (x)-> System.out.println(x);
     * 若只有一个参数，小括号可以省略不写，
     * x-> System.out.println(x);
     */
    @Test
    public void test2()
    {
        Consumer<String> con = (x)-> System.out.println(x);
        con.accept("威武 ");
    }

    /**
     * 语法格式三、有两个及以上的参数，有返回值，并且Lambda体中有多条语句
     * Comparator<Integer> com = (x,y)->{
     *             System.out.println("函数式接口");
     *             return Integer.compare(x,y);
     *         };
     */
    @Test
    public void test3()
    {
        Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };

    }
    /**
     * 语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
     */
    @Test
    public void test4()
    {
        Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
    }
}
