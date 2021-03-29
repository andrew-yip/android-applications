package com.example.backgroundthread_counting_practice;

public class Counter {

    private Integer mCountUpdate;

    public Counter () {
        mCountUpdate = 0;
    }

    public void setCountUpdate() {
        mCountUpdate++;
    }

    public Integer getCountUpdate() {
        return mCountUpdate;
    }

    public String toString () {
        return mCountUpdate.toString();
    }
}
