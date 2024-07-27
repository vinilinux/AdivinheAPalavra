package utils;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {
    private Timer timer;
    private long totalMillis;
    private boolean isRunning;

    public Cronometro(long totalMillis) {
        this.totalMillis = totalMillis;
        this.timer = new Timer();
        isRunning = false;
    }

    public boolean start() {
        if (!isRunning) {
            isRunning = true;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                    isRunning = false;
                }
            };
            timer.schedule(task, totalMillis);
        }
        return isRunning;
    }

    public void stop() {
        if (isRunning) {
            timer.cancel();
            isRunning = false;
        }
    }
}
