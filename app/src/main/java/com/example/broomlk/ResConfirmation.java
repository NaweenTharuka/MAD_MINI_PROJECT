package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ResConfirmation extends AppCompatActivity {
    private static final String TAG = "ResConfirmation";
    private TextView pD, rD;
    private EditText pNo;
    private Spinner s1, s2;
    private Button b4, b5, b6;
    private DatePickerDialog.OnDateSetListener DataSetListner3;
    private DatePickerDialog.OnDateSetListener DataSetListner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_confirmation);
        pD = (TextView) findViewById(R.id.pDate);
        rD = (TextView) findViewById(R.id.rDate);
        pNo = (EditText) findViewById(R.id.phone);
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        b4 = (Button) findViewById(R.id.btnsubmit);
        b5 = (Button) findViewById(R.id.btndel);
        b6 = (Button) findViewById(R.id.btnsearch);
    }
}
