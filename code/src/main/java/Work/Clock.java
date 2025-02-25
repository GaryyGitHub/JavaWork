package Work;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author ikun568
 * @version 1.0
 * @Description 显示时间
 **/
public class Clock extends Thread {
    public void run() {
        while (true) {
            GregorianCalendar time = new GregorianCalendar();
            int hour = time.get(Calendar.HOUR_OF_DAY);
            int min = time.get(Calendar.MINUTE);
            int second = time.get(Calendar.SECOND);
            Frame.tool_label_time.setText("  Time:" + hour + ":" + min + ":" + second);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                //发生错误时的信息提示
            }
        }
    }
}