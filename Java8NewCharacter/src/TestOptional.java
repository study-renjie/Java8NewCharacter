import org.junit.Test;

import java.util.Optional;

/**
 * @ClassName TestOptional
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/20
 **/
public class TestOptional {

    @Test
    public void test()
    {
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
    }

    @Test
    public void test1()
    {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test2()
    {
        Optional<Object> empty = Optional.empty();
        if(empty.isPresent())
        {
            System.out.println(empty.get());
        }
        else
            System.out.println("啥都没有");


    }
    @Test
    public void test3()
    {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        Employee employee = op.orElse(new Employee("sss",20,1111.11,Employee.Status.BUSY));
        System.out.println(employee);
    }

    @Test
    public void test4()
    {
        Optional<Employee> op = Optional.ofNullable(new Employee("sss",20,1111.11,Employee.Status.BUSY));
        Optional<String> str = op.map(Employee::getName);
        System.out.println(str.get());
    }

    @Test
    public void test5()
    {
        Optional<Employee> op = Optional.ofNullable(new Employee("sss",20,1111.11,Employee.Status.BUSY));
        Optional<String> str = op.flatMap((e)->Optional.of(e.getName()));
        System.out.println(str.get());
    }

    @Test
    public void test6()
    {
        Optional<NewMan> op = Optional.ofNullable(null);
        String str = getGodnessName(op);
        System.out.println(str);

    }

    private String getGodnessName(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("Miss Li"))
                .getName();
    }

}

