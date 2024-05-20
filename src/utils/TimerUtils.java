package utils;

import java.util.Date;

public abstract class TimerUtils {

    static private Date timer;

    static public void startTimer() {
        timer = new Date();
    }

    static public void endTimer() {
        Date currentDate = new Date();

        if (timer != null) {
            Long diffTime = (currentDate.getTime() - timer.getTime());
            Double secondsTime = (diffTime.doubleValue() / 1000);
            System.out.println("Tempo corrido: " + secondsTime + " segundos");
        }
    }
}
