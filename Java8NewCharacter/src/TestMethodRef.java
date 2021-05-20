/**
 * @ClassName TestMethodRef
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 *      可以将方法引用理解为Lambda表达式的另外一种表现形式
 *
 *
 * 主要有三种语法格式：
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名
 *
 * 注意事项：
 *      1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型要一致。
 *      2、若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个方法为实例方法的参数时，可以使用ClassName::methodName
 */
public class TestMethodRef {

    //对象::实例方法名
    //参数和返回值要和接口中的抽象方法一致
    @Test
    public void test1()
    {
        PrintStream ps = System.out;
        Consumer<String> con = (x)->ps.println(x);

        PrintStream ps1 = System.out;
        Consumer<String> con1 = (x)->ps1.println(x);

        Consumer<String> con2 = System.out::println;
        con2.accept("abcdef");
    }

    @Test
    public void test2()
    {
        Employee emp = new Employee("rj",30,111111.11);
        Supplier<String> sup = ()->emp.getName();
        System.out.println(sup.get());

        Supplier<Integer> sup2 = emp::getAge;
        System.out.println(sup2.get());
    }

    //类::静态方法名
    @Test
    public void test3()
    {
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com1.compare(1, 2));
    }

    //类::实例方法名
    @Test
    public void test4()
    {
        BiPredicate<String,String> bp = (x,y)->x.equals(y);
        //在上面使用的是对象::实例方法
        //在这里为什么可以使用类名::实例方法，因为需要满足第一个参数为实例方法的调用者，第二个参数为实例方法的参数时才可以使用类名::实例方法
        BiPredicate<String,String> bp1 = String::equals;
        System.out.println(bp1.test("nihao", "nihao"));
    }

}
