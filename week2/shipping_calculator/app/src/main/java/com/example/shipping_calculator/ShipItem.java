package com.example.shipping_calculator;

public class ShipItem {

    // SHIPPING CONSTANTS
    static final Double BASE = 3.00;
    static final Double added = .50;
    static final int BASE_WEIGHT = 16;
    static final double EXTRA_OUNCES = 4.0;

    // DATA MEMBERS
    private Integer mWeight;
    private Double mBaseCost;
    private Double mAddedCost;
    private Double mTotalCost;

    // INSTANTIATION
    public ShipItem(){
        mWeight = 0;
        mAddedCost = 0.0;
        mBaseCost = BASE;
        mTotalCost = 0.0;
    }

    // SETTERS
    public void setWeight (int weight){
        mWeight = weight;
        computeCosts(); // updates every time
    }

    // GETTERS
    public Double getBaseCost(){
        return mBaseCost;
    }

    public Double getAddedCost(){
        return mAddedCost;
    }

    public Double getTotalCost(){
        return mTotalCost;
    }

    // COMPUTATIONS
    private void computeCosts(){
        mAddedCost = 0.0;
        mBaseCost = BASE;

        if (mWeight <= 0){
            mBaseCost = 0.0;
        } else if (mWeight > BASE_WEIGHT){
            mAddedCost = Math.ceil((double) (mWeight - BASE_WEIGHT)/EXTRA_OUNCES) * added;
        }
        mTotalCost = mBaseCost*mAddedCost;
    }
}
