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
import java.util.List;

//Reservation details update
public class ResConfirmation extends AppCompatActivity {
    private static final String TAG = "ResConfirmation";
    private TextView pD, rD;
    private EditText pNo;
    private Spinner s1, s2;
    private Button b4, b5, b6, b7;
    private DatePickerDialog.OnDateSetListener DataSetListner3;
    private DatePickerDialog.OnDateSetListener DataSetListner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_confirmation);
        pD = (TextView) findViewById(R.id.pDate); //cast pickup date
        rD = (TextView) findViewById(R.id.rDate); //cast return date
        pNo =(EditText) findViewById(R.id.editphone); //cast phone number
        s1 = (Spinner) findViewById(R.id.spinner1); //cast vehicle model
        s2 = (Spinner) findViewById(R.id.spinner2); //cast vehicle sub model
        b4 = (Button) findViewById(R.id.btnsubmit); //cast submit button
        b5 = (Button) findViewById(R.id.btndel); //cast delete reservation button
        b6 = (Button) findViewById(R.id.btnsearch); //cast search reservation button




        pD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ResConfirmation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner3,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DataSetListner3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                pD.setText(date);


            }
        };


        rD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ResConfirmation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DataSetListner4,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        DataSetListner4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:mm/dd/yyyy:" + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                rD.setText(date);


            }
        };

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ResConfirmation.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Cars));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(myAdapter);


        final ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(ResConfirmation.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.model));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(myAdapter1);



        //update reservation details
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReservationDatabase reservationDatabase = new ReservationDatabase(getApplicationContext());
                List phonenumber = reservationDatabase.readAllInfo(pNo.getText().toString()); //get data from reservation phone number

                if (phonenumber.isEmpty()){ //check whether phone number is Empty
                    Toast.makeText(ResConfirmation.this, "No Reservation", Toast.LENGTH_SHORT).show(); //toast message for empty details
                    pNo.setText(null);
                }
                else {

                    Toast.makeText(ResConfirmation.this, "Reservation Details Found.....!    Mobile Number: "+phonenumber.get(0).toString(), Toast.LENGTH_SHORT).show(); //toast message for successfully found details
                    pNo.setText(phonenumber.get(0).toString());
                    pD.setText(phonenumber.get(1).toString());
                    rD.setText(phonenumber.get(2).toString());
                    s1.setSelection(myAdapter.getPosition(phonenumber.get(3).toString()));;
                    s2.setSelection(myAdapter1.getPosition(phonenumber.get(4).toString()));;




                }

            }
        });

        //search data from phone number
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(pNo.getText())) {
                    pNo.setError("Please Search your Phone Number!");
                    pNo.requestFocus();
                } else {

                ReservationDatabase reservationDatabase = new ReservationDatabase(getApplicationContext());

                Boolean status = reservationDatabase.updateInfo(pNo.getText().toString(), pD.getText().toString(), rD.getText().toString(), s1.getSelectedItem().toString(), s1.getSelectedItem().toString());
                if (status) {
                    Toast.makeText(ResConfirmation.this, "Reservation Details Updated", Toast.LENGTH_SHORT).show(); // toast message for successfully updated
                    Intent i = new Intent(getApplicationContext(), Payment.class);
                    startActivity(i);
                } else {
                    Toast.makeText(ResConfirmation.this, "Reservation Update Failed", Toast.LENGTH_SHORT).show(); //toast message for successfully deleted
                }
            }
            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(pNo.getText())){
                    pNo.setError("Please Search your Phone Number!");
                    pNo.requestFocus();}
                else {

                    ReservationDatabase reservationDatabase = new ReservationDatabase(getApplicationContext());
                    reservationDatabase.deleteInfo(pNo.getText().toString());


                    Toast.makeText(ResConfirmation.this, "Reservation Details Deleted", Toast.LENGTH_SHORT).show(); //toast message for successfully delete reservation
                    Intent i = new Intent(getApplicationContext(), Available.class);
                    startActivity(i);

                    pNo.setText(null);
                    pD.setText(null);
                    rD.setText(null);
                    s1.getSelectedItem();
                    s1.getSelectedItem();
                }
            }
        });

    }
}
