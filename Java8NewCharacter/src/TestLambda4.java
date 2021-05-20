import org.junit.Test;

import java.util.Locale;

/**
 * @ClassName TestLambda4
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/18
 **/
@FunctionalInterface
interface MyFunction
{
    public String getValue(String str);
}
public class TestLambda4 {
    /*
     - 1、声明函数式接口，接口中声明抽象方法，public String getValue(String str);
     - 2、声明类TestLambda，类中编写方法使用接口作为参数，将一个字符串转化为大写，并作为方法的返回值。
     - 3、再将一个字符串的第2个和第4个索引位置进行截取子串。
    */
    public String strHandler(String str,MyFunction mf)
    {
        return mf.getValue(str);
    }

    @Test
    public void test()
    {
        String trimStr = strHandler("\t\t\t abcdef",str-> str.trim());
        String upper = strHandler(trimStr,str-> str.toUpperCase());
        String newStr = strHandler(upper,str-> str.substring(2,5));
        System.out.println(newStr);
    }



    @Test
    public void test3()
    {
        op(100L,200L,(x,y)->x+y);
        op(100L,200L,(x,y)->x*y);
    }
    @FunctionalInterface
    interface MyFunction2
    {
        public long getvalue(long l1,long l2);
    }
    public void op(long l1,long l2,MyFunction2 mf)
    {
        System.out.println(mf.getvalue(l1,l2));
    }


}
