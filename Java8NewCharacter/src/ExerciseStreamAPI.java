import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @ClassName ExerciseStreamAPI
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/19
 **/
public class ExerciseStreamAPI {
    //1、给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
    //2、给定[1，2，3，4，5]，应该返回[1,4,9,16,25]
    @Test
    public void test()
    {
        //通过Stream来做
        Integer[] nums = {1,2,3,4,5};
        Arrays.stream(nums).map(x->x*x).forEach(System.out::println);
    }

    List<Employee> emps = Arrays.asList(
            new Employee("张三",18,9999.99, Employee.Status.FREE),
            new Employee("李四",59,6666.66,Employee.Status.BUSY),
            new Employee("王五",28,3333.33,Employee.Status.VOCATION),
            new Employee("赵六",8,7777.77,Employee.Status.FREE),
            new Employee("田七",38,5555.55,Employee.Status.BUSY)
    );
    @Test
    public void test2()
    {
        Optional<Integer> reduce = emps.stream().map(x -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}
