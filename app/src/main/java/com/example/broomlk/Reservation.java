package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Reservation extends AppCompatActivity {
    private static final String TAG = "Reservation";

    private TextView pD, rD;
    private EditText pNo;
    private Spinner s1, s2;
    private Button b1, b2, b3;
    private DatePickerDialog.OnDateSetListener DataSetListner1;
    private DatePickerDialog.OnDateSetListener DataSetListner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        pD = (TextView) findViewById(R.id.pDate);
        rD = (TextView) findViewById(R.id.rDate);
        pNo = (EditText) findViewById(R.id.phone);
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        b1 = (Button) findViewById(R.id.btnadd);
        b2 = (Button) findViewById(R.id.btnview);
        b3 = (Button) findViewById(R.id.btnedit);




        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Reservation.this, ResConfirmation.class);
                startActivity(continueIntent);
            }
        });


    }
}
