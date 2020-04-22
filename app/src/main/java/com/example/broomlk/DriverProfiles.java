package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class DriverProfiles extends AppCompatActivity {

    EditText drivername,dob,password,nic,age;
    Button add,updateprofile;
    RadioButton male,female;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profiles);

        drivername = findViewById(R.id.editText3);
        dob = findViewById(R.id.editText6);
        password = findViewById(R.id.editText13);
        nic = findViewById(R.id.editText14);
        age = findViewById(R.id.editText15);
        add = findViewById(R.id.button4);
        updateprofile = findViewById(R.id.button9);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);

        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EdiPro.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender = "Male";
                }
                else{
                    gender="Female";
                }
                if(TextUtils.isEmpty(drivername.getText())){
                    drivername.setError("Please Enter User Name");
                    drivername.requestFocus();
                }
                else if(TextUtils.isEmpty(dob.getText())){
                    dob.setError("Please Enter BirthDay");
                    dob.requestFocus();
                }
                else if(TextUtils.isEmpty(password.getText())){
                    password.setError("Please Enter password");
                    password.requestFocus();
                }
                else if(TextUtils.isEmpty(nic.getText())){
                    nic.setError("Please Enter NIC");
                    nic.requestFocus();
                }
                else if(TextUtils.isEmpty(age.getText())){
                    age.setError("Please Enter Age");
                    age.requestFocus();
                }
                else {
                    DBRHandler dbrHandler = new DBRHandler(getApplicationContext());
                    Long newID = dbrHandler.addInfo(drivername.getText().toString(), dob.getText().toString(), password.getText().toString(), nic.getText().toString(), age.getText().toString(), gender);
                    Toast.makeText(DriverProfiles.this, "Driver Added. User ID:" + newID, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), EdiPro.class);
                    startActivity(i);

                    drivername.setText(null);
                    dob.setText(null);
                    password.setText(null);
                    nic.setText(null);
                    age.setText(null);
                    male.setChecked(false);
                    female.setChecked(false);
                }
            }
        });
    }
}