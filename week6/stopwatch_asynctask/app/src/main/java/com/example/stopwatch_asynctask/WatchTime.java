package com.example.stopwatch_asynctask;

public class WatchTime {

    // TIME ELEMENTS
    private long mStartTime;
    private long mTimeUpdate;
    private long mStoredTime;
    public boolean timeFlowing;

    public WatchTime () {
        mStartTime = 0L;
        mTimeUpdate = 0L;
        mStoredTime = 0L;
        timeFlowing = false;
    }

    public void resetWatchTime() {
        mStartTime = 0L;
        mTimeUpdate = 0L;
        mStoredTime = 0L;
        timeFlowing = false;
    }

    public void setStartTime(long startTime) {
        mStartTime = startTime;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setTimeUpdate(long timeUpdate) {
        mTimeUpdate = timeUpdate;
    }

    public long getTimeUpdate() {
        return mTimeUpdate;
    }

    public void addStoredTime(long timeInMilliseconds) {
        mStoredTime += timeInMilliseconds;
    }

    public long getStoredTime() {
        return mStoredTime;
    }
}
