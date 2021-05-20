/**
 * @ClassName TestLambda5
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的四大函数式接口
 * Consumer<T>:消费型接口
 *      void accept(T t);
 *
 * Supplier<T>:供给型接口
 *      T get();
 *
 * Function<T,R>:函数型接口
 *      R apply(T t);
 *
 * Predicate<T>:断言型接口
 *      boolean test(T t);
 */
public class TestLambda5 {
    //Consumer<T> 消费型接口：
    @Test
    public void test1()
    {
        happy(1000,m-> System.out.println("消费"+m+"元"));
    }
    public void happy(double money, Consumer<Double> con)
    {
        con.accept(money);
    }


    //Supplier<T>:供给型接口
    //供给什么主要看Lambda怎么实现
    @Test
    public void test2()
    {
        //实现Supplier里的get方法
        List<Integer> numList = getNumList(10,()->(int)(Math.random()*100));
        for(Integer num:numList)
        {
            System.out.println(num);
        }
    }
    //需求：产生指定个数的整数并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<num;i++)
        {
            list.add(sup.get());
        }
        return list;
    }

    //Function<T,R>:函数型接口
    //传进去一个T，返回一个R
    //需求：用于处理字符串
    @Test
    public void test3()
    {
        //去除首尾空格
        String newStr = strHandler("\t\t\t strHandler威武威武威武",str->str.trim());
        //获取字符串的2-5位置
        String subStr = strHandler(newStr,str->str.substring(2,5));
        System.out.println(newStr);
        System.out.println(subStr);

    }
    public String strHandler(String str, Function<String,String> fun)
    {
        return fun.apply(str);
    }

    //Predicate<T>:断言型接口
    //      boolean test(T t);
    //需求：将满足条件的字符串放入到集合中去
    @Test
    public void test4()
    {
        List<String> list = Arrays.asList("Hello","nihao","atguigu","Lambda","www","ok");
        List<String> res = filterStr(list, s -> s.length() > 3);
        for (String str:res)
        {
            System.out.println(str);
        }

    }
    public List<String> filterStr(List<String> list, Predicate<String> pre)
    {
        List<String> strList = new ArrayList<>();
        for(String str:list)
        {
            if (pre.test(str))
                strList.add(str);
        }
        return strList;
    }

}
