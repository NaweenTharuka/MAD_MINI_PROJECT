package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

//Vehicle Reservation
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

        pD = (TextView) findViewById(R.id.pDate); //cast pickup date
        rD = (TextView) findViewById(R.id.rDate); //cast Return Date
        pNo = (EditText) findViewById(R.id.phone); //cast Phone number
        s1 = (Spinner) findViewById(R.id.spinner1); //cast vehicle model
        s2 = (Spinner) findViewById(R.id.spinner2); //cast vehicle sub model

        b1 = (Button) findViewById(R.id.btnadd); //cast Add Reservation Button
        b3 = (Button) findViewById(R.id.btnedit); //cast Edit Reservation

        //link to Edit reservation
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Reservation.this, ResConfirmation.class);
                startActivity(continueIntent);
            }
        });

        //calender view for pickup and return date
        pD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Reservation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DataSetListner1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year; //get date from calender
                pD.setText(date); //set date to string


            }
        };

        rD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR); //get year
                int month = cal.get(Calendar.MONTH); //get month
                int day = cal.get(Calendar.DAY_OF_MONTH); //get date

                DatePickerDialog dialog = new DatePickerDialog(
                        Reservation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner2,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DataSetListner2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                rD.setText(date);

            }
        };

        //define array to values for vehicle model dropdown
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Reservation.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Cars));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(myAdapter);

        //define array to values for vehicle sub model dropdown
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Reservation.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.model));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(myAdapter1);

        //get reservation details
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(pD.getText())){
                    pD.setError("Enter Pick-Up Date!"); //check whether pickup date is entered
                    pD.requestFocus();}
                else if(TextUtils.isEmpty(rD.getText())){
                        rD.setError("Enter Return Date!"); //check whether return date is entered
                        rD.requestFocus(); }
                else if(TextUtils.isEmpty(pNo.getText())){
                        pNo.setError("Enter Your Phone Number!"); //check whether phone number is entered
                        pNo.requestFocus(); }
                else {

                    ReservationDatabase reservationDatabase = new ReservationDatabase(getApplicationContext());
                    long newID = reservationDatabase.addInfo(pNo.getText().toString(), pD.getText().toString(), rD.getText().toString(), s1.getSelectedItem().toString(), s2.getSelectedItem().toString());
                    Toast.makeText(Reservation.this, "Reservation Successfully......!    Reservation ID: " + newID, Toast.LENGTH_SHORT).show(); //success toast message with reservation ID

                    Intent i = new Intent(getApplicationContext(), ResConfirmation.class);
                    startActivity(i);
                    pNo.setText(null);
                    pD.setText(null);
                    rD.setText(null);
                    s1.getSelectedItem();
                    s2.getSelectedItem();

                }
            }
        });


    }
}
