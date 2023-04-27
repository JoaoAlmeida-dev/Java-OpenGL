package org.lwjglb.engine;

public class Timer {
    private double lastLoopTime;

    public static double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }

    public void init() {
        lastLoopTime = getTime();
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }

}
