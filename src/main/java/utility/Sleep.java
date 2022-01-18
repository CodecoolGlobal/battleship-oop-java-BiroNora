package main.java.utility;

public class Sleep {
    public static void goToSleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            // ...do nothing
        }
    }
}
