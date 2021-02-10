package com.example.automotive_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    // THE AUTO OBJECT CONTAINS THE INFORMATION ABOUT THE VEHICLE BEING PURCHASED
    Auto mAuto;

    // THE DATA TO BE PASSED TO THE LOAN ACTIVITY
    String loanReport;
    String monthlyPayment;

    // LAYOUT INPUT REFERENCES
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ESTABLISH REFERENCES TO EDITABLE TEXT FIELDS AND RADIO BUTTON
        carPriceET = (EditText) findViewById(R.id.editText1);
        downPayET = (EditText) findViewById(R.id.editText2);
        loanTermRG = (RadioGroup) findViewById(R.id.radioGroup1);

        // CREATE AN AUTOMOBILE OBJECT TO STORE AUTO DATA
        mAuto = new Auto();
    }

    private void collectAutoInputData() {
        // TASK 1: SET THE CAR PRICE
        mAuto.setPrice ((double) Integer.valueOf(carPriceET.getText().toString()));

        // TASK 2: SET THE DOWN PAYMENT
        mAuto.setDownPayment((double) Integer.valueOf(downPayET.getText().toString()));

        // TASK 3: SET THE LOAN TERM
        Integer radioId = loanTermRG.getCheckedRadioButtonId(); // gets the ID of the button selected in the radio group
        RadioButton term = (RadioButton) findViewById(radioId); // selects the radio button based on ID
        mAuto.setLoanTerm(term.getText().toString()); // sets the term to the selected option
    }

    private void buildLoanReport () {
        // TASK 1: CONSTRUCT THE MONTHLY PAYMENT
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f", mAuto.monthlyPayment());

        // TASK 2: CONSTRUCT THE LOAN REPORT
        loanReport = res.getString(R.string.report_line6)+String.format("%10.02f", mAuto.getPrice());
        loanReport += res.getString(R.string.report_line7) + String.format("%10.2f", mAuto.getDownPayment());

        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.taxAmount());
        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.totalCost());
        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.borrowedAmount());
        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.interestAmount());

        loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + mAuto.getLoanTerm() + " years.";

        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += "\n\n" + res.getString(R.string.report_line3);
        loanReport += "\n\n" + res.getString(R.string.report_line4);
        loanReport += "\n\n" + res.getString(R.string.report_line5);
    }

    public void activateLoanSummary(View view) {
        // TASK 1: BUILD A LOAN REPORT FROM THE INPUT DATA
        collectAutoInputData();
        buildLoanReport();

        // TASK 2: CREATE AN INTENT TO DISPLAY THE LOAN SUMMARY ACTIVITY
        Intent launchReport = new Intent(this, LoanSummaryActivity.class);

        // TASK 3: PASS THE LOAN SUMMARY ACTIVITY TWO PIECES OF DATA: THE LOAN REPORT CONTAINING LOAN DETAILS
        // AND THE MONTHLY PAYMENT
        launchReport.putExtra("LoanReport", loanReport);
        launchReport.putExtra("MonthlyPayment", monthlyPayment);

        // TASK 4: START THE LOAN ACTIVITY
        startActivity(launchReport);
    }
}