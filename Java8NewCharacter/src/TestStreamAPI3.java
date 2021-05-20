import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TestStreamAPI3
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/19
 **/
public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三",18,9999.99, Employee.Status.FREE),
            new Employee("李四",59,6666.66,Employee.Status.BUSY),
            new Employee("王五",28,3333.33,Employee.Status.VOCATION),
            new Employee("赵六",8,7777.77,Employee.Status.FREE),
            new Employee("田七",38,5555.55,Employee.Status.BUSY)
    );
    /*
    查找与匹配
    allMatch--检查是否匹配所有元素
    anyMatch--检查是否至少匹配一个元素
    noneMatch--检查是否没有匹配所有元素
    findFirst--返回第一个元素
    findAny--返回当前流中的任意元素
    count--返回流中元素的总个数
    max--返回流中最大值
    min--返回流中最小值
     */

    @Test
    public void test1()
    {
        boolean b1 = emps.stream().allMatch(e->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        boolean b2 = emps.stream().anyMatch(e->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        boolean b3 = emps.stream().noneMatch(e->e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        //为了防止为空，所以返回了Optional
        Optional<Employee> op = emps.stream()
                .sorted((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary()))
                .findFirst();
        System.out.println(op.get());

        Optional<Employee> op2 = emps.stream()
                .filter(e->e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2.get());
    }

    @Test
    public void test2()
    {
        Long count = emps.stream().count();
        System.out.println(count);
        Optional<Employee> op1 = emps.stream().max((e1,e2)->{
            return Double.compare(e1.getSalary(),e2.getSalary());
        });
        System.out.println(op1.get());
        Optional<Double> op2 = emps.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(op2.get());

    }

    @Test
    public void test3()
    {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //1作为x，list中取出第一个值作为y，相加得到的结果作为x，再在List中取出第二个元素作为y，....
        Integer res = list.stream().reduce(1,(x,y)->x+y);
        System.out.println(res);
        System.out.println("--------------------------");
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }


    /*
    collect--将流转换为其它形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
    */
    @Test
    public void test4()
    {
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------------------------");
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("--------------------");
        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);

    }

    @Test
    public void test5()
    {
        //总数
        Long count = emps.stream().collect(Collectors.counting());
        System.out.println(count);
        System.out.println("----------------------");
        //平均值
        Double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        //总和
        Double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        //最大值
        Optional<Employee> max = emps.stream().collect(Collectors.maxBy((e1,e2)->Double.compare(e1.getSalary(),e2.getSalary())));
        System.out.println(max.get());
        //最小值
        Optional<Double> min = emps.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    //分组
    @Test
    public void test6()
    {
        //按照Status进行分组
        Map<Employee.Status,List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }
    //多级分组
    @Test
    public void test7()
    {
        Map<Employee.Status,Map<String,List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy(e->{
                    if(((Employee)e).getAge()<=35)
                        return "青年";
                    else if(((Employee)e).getAge()<=50)
                        return "中年";
                    else
                        return "老年";
                })));
        System.out.println(map);
    }

    @Test
    public void test8()
    {
        Map<Boolean,List<Employee>> map = emps.stream().collect(Collectors.partitioningBy(e->e.getSalary()>8000));
        System.out.println(map);
    }

    @Test
    public void test9()
    {
        String str = emps.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(str);
    }


}
