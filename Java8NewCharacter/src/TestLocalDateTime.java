import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;
import java.util.function.Function;

/**
 * @ClassName TestLocalDateTime
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/22
 **/
public class TestLocalDateTime {

    @Test
    public void test()
    {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2021,5,22,14,20,16);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt2.plusYears(2);
        System.out.println(ldt3);
        LocalDateTime ldt4 = ldt3.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt4.getYear());
        System.out.println(ldt4.getMonthValue());
        System.out.println(ldt4.getDayOfMonth());
        System.out.println(ldt4.getHour());
        System.out.println(ldt4.getMinute());
        System.out.println(ldt4.getSecond());

    }

    @Test
    public void test2()
    {
        Instant ins1 = Instant.now(); //默认获取UTC时区
        System.out.println(ins1);
        //将日期格式转换成时间戳格式
        System.out.println(ins1.toEpochMilli());

        //带偏移量的时间日期
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(2));
        System.out.println(odt);

        //将秒转换为日期格式
        Instant instant = Instant.ofEpochSecond(61);
        System.out.println(instant);
    }

    @Test
    public void test3()
    {
        //默认获取etc时区
        Instant ins1 = Instant.now();
        System.out.println(ins1);
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        System.out.println(ins1.toEpochMilli());

    }

    //Duration：计算两个"时间"之间的间隔
    //Period：计算两个日期之间的间隔
    @Test
    public void test4()
    {
        //默认获取etc时区
        Instant ins1 = Instant.now();
        System.out.println(ins1);

        try {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {

        }
        Instant ins2 = Instant.now();
        Duration between = Duration.between(ins1, ins2);
        System.out.println(between.toMillis());

    }

    @Test
    public void test5()
    {
        LocalDate ld1 = LocalDate.of(2015,1,1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1,ld2);
        //P6Y4M21D表示period 1Year 4Month 21Day
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    //时间矫正器
    @Test
    public void test6()
    {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        //将日期指定成10号
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);
        //将日期修改为下一个sunday
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with(l->
        {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY))
                return ldt4.plusDays(3);
            else if(dow.equals(DayOfWeek.SATURDAY))
                return ldt4.plusDays(2);
            else
                return ldt4.plusDays(1);
        });
        System.out.println(ldt5);

    }

    //DateTimeFormatter:格式化时间/日期
    @Test
    public void test7()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        String format = ldt.format(dtf);
        System.out.println(format);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format2 = ldt.format(dtf2);
        System.out.println(format2);
        LocalDateTime res = ldt.parse(format2,dtf2);
        System.out.println(res);


    }

    @Test
    public void test8()
    {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::print);


    }



    @Test
    public void test9()
    {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);



    }



}
