import org.junit.Test;

import java.util.*;

/**
 * @ClassName Lambda
 * @Description: TODO
 * @Author renjie
 * @Date 2021/4/25
 **/
public class Lambda {
    //原来的匿名内部类
    @Test
    public void test1()
    {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //Lambda表达式
    //使用Lambda使用的代码量少了
    @Test
    public void test2()
    {
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
        new Employee("张三",18,9999.99),
        new Employee("李四",38,5555.55),
        new Employee("王五",50,6666.66),
        new Employee("赵六",16,3333.33),
        new Employee("田七",8,7777.77)
    );

    //需求：获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployees(List<Employee> list)
    {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp:list)
        {
            if(emp.getAge()>=35)
            {
                emps.add(emp);
            }
        }
        return emps;
    }

    //需求：获取当前公司员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list)
    {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp:list)
        {
            //唯一和上面一个函数不同的一行，需求变动可能对代码的影响很小，但是要重新写一个函数
            if(emp.getSalary()>=5000)
            {
                emps.add(emp);
            }
        }
        return emps;
    }


    //优化方式一：策略模式

    public interface MyPredicate<Employee> {
        public boolean test(Employee employee);
    }

    class FilterEmployeeByAge implements MyPredicate<Employee>
    {

        @Override
        public boolean test(Employee employee) {
            return employee.getAge()>=35;
        }
    }

    //将Java类按照MyPredicate的方式进行过滤
    //方法只要写这一个，传入不同的类，即可按照不同的方式进行过滤
    public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp)
    {
        List<Employee> emps = new ArrayList<>();
        for (Employee employee:list)
        {
            if (mp.test(employee))
            {
                emps.add(employee);
            }
        }
        return emps;
    }

    //优化方式二：匿名内部类
    @Test
    public void test5()
    {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary()<=5000;
            }
        });

        for (Employee employee:list)
        {
            System.out.println(employee);
        }
    }

}
