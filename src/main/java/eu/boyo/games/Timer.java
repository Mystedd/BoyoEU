package eu.boyo.games;

public class Timer {

    double startTime;
    double offset;
    boolean isEnabled;

    public Timer() {
        isEnabled = false;
    }

    public void start() {
        isEnabled = true;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        offset += startTime - System.currentTimeMillis();
        isEnabled = false;
    }

    public void setTime(double newTime) {
        startTime = System.currentTimeMillis();
        offset = newTime;
    }

    public double getTime() {
        if (isEnabled) {
            return ((startTime - System.currentTimeMillis()) + offset);
        } else {
            return (offset);
        }
    }

}
