import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateFormatThreadLocal
 * @Description: TODO
 * @Author renjie
 * @Date 2021/5/22
 **/
public class DateFormatThreadLocal {
    public static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }


}
