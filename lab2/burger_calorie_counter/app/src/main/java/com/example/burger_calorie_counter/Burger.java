package com.example.burger_calorie_counter;

public class Burger {

    static final int BEEF = 100;
    static final int LAMB = 170;
    static final int OSTRICH = 150;
    static final int ASIAGO = 90;
    static final int CREME_FRAICHE = 120;
    static final int PROSCIUTO = 115;

    // DATA MEMBERS
    private int mPattyCal;
    private int mCheeseCal;
    private int mProsciuttoCal;
    private int mSauceCal;

    // DEFAULT CONSTRUCTOR
    public  Burger (){
        mPattyCal = BEEF;
        mCheeseCal = ASIAGO;
        mProsciuttoCal = 0;
        mSauceCal = 0;
    }

    // SETTERS

    public void setmPattyCal(int mPattyCal) {
        this.mPattyCal = mPattyCal;
    }

    public void setmCheeseCal(int mCheeseCal) {
        this.mCheeseCal = mCheeseCal;
    }

    public void setmProsciuttoCal(int mProsciuttoCal) {
        this.mProsciuttoCal = mProsciuttoCal;
    }

    public void setmSauceCal(int mSauceCal) {
        this.mSauceCal = mSauceCal;
    }

   // GETTERS

    public int getmPattyCal() {
        return mPattyCal;
    }

    public int getmCheeseCal() {
        return mCheeseCal;
    }

    public int getmProsciuttoCal() {
        return mProsciuttoCal;
    }

    public int getmSauceCal() {
        return mSauceCal;
    }

    // CLEAR PROSCUITTO CALOFIRES
    public void clearProsciuttoCalories () {
        mProsciuttoCal = 0;
    }

    // RETURN TOTAL # OF CALORIES
    public int getTotalCalories () {
        return mPattyCal + mCheeseCal + mProsciuttoCal + mSauceCal;
    }
}
