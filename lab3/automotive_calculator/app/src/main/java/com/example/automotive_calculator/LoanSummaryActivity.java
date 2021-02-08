package com.example.automotive_calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class LoanSummaryActivity extends Activity {

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loansummary_layout);
        TextView monthlyPayET = (TextView) findViewById(R.id.textView2);
        TextView loanReportET = (TextView) findViewById(R.id.textView3);

        // PASS DATA
        Intent intent = getIntent();

        String report;
        report = intent.getStringExtra("LoanReport"); // PASSED FROM MAIN ACTIVITY FILE

        String monthlyPay;
        monthlyPay = intent.getStringExtra("MonthlyPayment"); // PASSED FROM MAIN
        monthlyPayET.setText(monthlyPay);
        loanReportET.setText(report);
    }

    public void goDataEntry(View view) {
        finish();
    }


}
