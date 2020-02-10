package imgpro;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp {

    public String rand;

    public String setTime() {
        rand = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        System.out.println(rand);
        long seconds = System.currentTimeMillis() / 1000l;
        System.out.println(seconds);
        rand=rand+seconds;
        return rand;

    }
}
