import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName TestStreamAPI2
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/
public class TestStreamAPI2 {
    //中间操作
    List<Employee> emps = Arrays.asList(
            new Employee("张三",18,9999.99),
            new Employee("李四",59,6666.66),
            new Employee("王五",28,3333.33),
            new Employee("赵六",8,7777.77),
            new Employee("田七",38,5555.55)
    );
    /*
    筛选与切片
    filter--接收Lambda，从流中删除某些元素
    limit--截断流，使其元素不超过给定数量
    skip(n)--跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
    distinct--筛选，通过流所生成元素的hashCode和equals去除重复元素
     */
    @Test
    public void test()
    {
        //中间操作，该操作不会有任何结果。
        Stream<Employee> stream = emps.stream().filter(x->{
            System.out.println("Stream API的中间操作");
            return x.getAge()>35;
        });
        //终止操作，终止操作执行的时候中间操作才会执行。
        stream.forEach(System.out::println);

    }

    //外部迭代
    @Test
    public void test1()
    {
        Iterator<Employee> iterator = emps.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    //limit+短路
    //一旦找到limit所限制的个数，那么迭代操作就不执行了
    @Test
    public void test3()
    {
        emps.stream()
                .filter(x->{
                    System.out.println("短路");
                    return x.getSalary()>1000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4()
    {
        emps.stream()
                .filter(x->x.getSalary()>1000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test5()
    {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream()
                .map(x->x.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-------------------------------------");

        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }


    public static Stream<Character> filterCharacter(String str)
    {
        List<Character> list = new ArrayList<>();
        for(Character ch:str.toCharArray())
        {
            list.add(ch);
        }
        return list.stream();
    }
    @Test
    public void test6()
    {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        //map本身就返回流，filterCharacter又返回流，流套流
        //{{a,a,a},{b,b,b},{c,c,c},{d,d,d}}
        //相当于大流中套了多个小流
        Stream<Stream<Character>> stream = list.stream().map(TestStreamAPI2::filterCharacter);
        //遍历
        stream.forEach(sm->{
            sm.forEach(System.out::println);
        });

    }

    @Test
    public void test7()
    {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");

        //{{a,a,a},{b,b,b},{c,c,c},{d,d,d}}
        //flatMap相当于把这些流整合成一个流
        //{a,a,a,b,b,b,c,c,c,d,d,d}
        //flatMap相当于把流中的元素添加进去
        Stream<Character> stream = list.stream().flatMap(TestStreamAPI2::filterCharacter);
        //遍历
        stream.forEach(System.out::println);
    }

    @Test
    public void test8()
    {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("-----------------------");

        emps.stream().sorted((e1,e2)->{
            if(e1.getAge()==e2.getAge())
                return e1.getName().compareTo(e2.getName());
            else
                return e1.getAge()-e2.getAge();
        }).forEach(System.out::println);

    }
}
