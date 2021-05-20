import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName TestConstructorRef
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/
public class TestConstructorRef {
    //构造器引用
    //注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致。
    @Test
    public void test5()
    {
        Supplier<Employee> sup = ()->new Employee("rj",30,111111.11);
        //构造器引用方式
        //下面使用的是无参构造器
        Supplier<Employee> sup1 = Employee::new;
        Employee employee = sup1.get();
        System.out.println(employee);

        //下面使用的是一个参数的构造器，因为取决于Function接口
        Function<Integer,Employee> fun = Employee::new;
        System.out.println(fun.apply(101));
    }
}
