package com.example.tap_button_counter;

public class Counter {

    private int mCount;

    public Counter(){
        mCount = 0;
    }

    public void addCount (){
        mCount++;
    }

    public Integer getCount(){
        return mCount;
    }
}
