package com.example.bmi_calculator_test;

public class BMI {

    // DATA MEMBERS
    public String units;
    public Integer inches;
    public Integer weight;

    private final int MIN = 0;

    public BMI () {
        units = "standard"; // DEFAULT UNITS IS STANDARD
        inches = 0;
        weight = 0;
    }

    // INCREMENT AND DECREMENT
    public void incrementInches() {
        inches++;
    }

    public void decrementInches () {
        if (inches > MIN){
            inches--;
        }
    }

    public void incrementPounds () {
        weight++;
    }

    public void decrementPounds () {
        if (weight > MIN) {
            weight--;
        }
    }

    // CALCULATE TOTAL
    public double calculateTotal() {
        if (units.equals("standard")){
            return  (weight.doubleValue()/ (inches.doubleValue()*inches.doubleValue()))*703;
        } else {
            return (weight.doubleValue()/ (inches.doubleValue()*inches.doubleValue()))*10000;
        }
    }

    // SETTERS AND GETTERS

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getInches() {
        return inches;
    }

    public void setInches(Integer inches) {
        this.inches = inches;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
