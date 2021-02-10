package com.example.calculator_java;

public class Calculation {

    private Integer mOperand1; // THE FIRST NUMBER
    private Integer mOperand2; // THE SECOND NUMBER
    private String mOperator; // THE OPERAND BETWEEN THE TWO
    private Integer mValue; // THE VALUE OF THE TWO NUMBERS (sum, product, difference, quotient, remainder)

    public Calculation() {
        mOperand1 = 0;
        mOperand2 = 0;
        mOperator = "+";
        mValue = 0;
    }

    // SETTERS AND GETTERS

    // setting the first value
    public void setOperand1 (int v) {
        mOperand1 = v;
    }

    public int getOperand1() {
        return mOperand1;
    }

    // setting the second value
    public void setOperand2(int v) {
        mOperand2 = v;
    }

    // either a +, -, /, *, %
    public void setOperator(String s) {
        mOperator = s;
    }

    // either a +, -, /, *, %
    public String getOperator() {
        return mOperator;
    }

    // RETURNS THE TOTAL
    public Integer getmValue() {
        computeValue();
        return mValue;
    }

    public void setmValue(Integer mValue) {
        this.mValue = mValue;
    }

    // CLEARS OPERAND (when clicking AC)
    public void clearOperands() {
        mOperand1 = 0;
        mOperand2 = 0;
    }

    // GETS CALLED INSIDE getmValue (computing mValue)
    private void computeValue () {
        mValue = 0;
        if (mOperator.contentEquals("+")){
            mValue = mOperand1 + mOperand2;
        } else if (mOperator.contentEquals("-")){
            mValue = mOperand1 - mOperand2;
        } else if (mOperator.contentEquals("x")){
            mValue = mOperand1 * mOperand2;
        } else if (mOperator.contentEquals("/") && mOperand2 != 0){
            mValue = mOperand1 / mOperand2;
        } else {
            mValue = mOperand1 % mOperand2;
        }
    }
}
