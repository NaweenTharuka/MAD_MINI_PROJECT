package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

//Edit driver profile
public class EdiPro extends AppCompatActivity {
    EditText drivername,dob,password,nic,age;
    Button submit,delete,search;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edi_pro);

        drivername = findViewById(R.id.editText17);
        dob = findViewById(R.id.editText18);
        password = findViewById(R.id.editText22);
        nic = findViewById(R.id.editText20);
        age = findViewById(R.id.editText21);
        search = findViewById(R.id.button3);
        submit = findViewById(R.id.button10);
        delete = findViewById(R.id.button11);
        male = findViewById(R.id.radioButton5);
        female = findViewById(R.id.radioButton6);

        //search driver details
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBRHandler dbrHandler = new DBRHandler(getApplicationContext());
                List user = dbrHandler.readAllInfo(drivername.getText().toString());

                if (user.isEmpty()){
                    Toast.makeText(EdiPro.this, "No Driver !", Toast.LENGTH_SHORT).show();
                    drivername.setText(null);
                }
                else{
                    Toast.makeText(EdiPro.this, "Driver Found ! User :"+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    drivername.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());
                    nic.setText(user.get(3).toString());
                    age.setText(user.get(4).toString());
                    if(user.get(5).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else{
                        female.setChecked(true);
                    }
                }
            }
        });

        //update driver details
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender="Male";
                }
                else {
                    gender="Female";
                }
                DBRHandler dbrHandler = new DBRHandler(getApplicationContext());
                Boolean status = dbrHandler.updateInfo(drivername.getText().toString(),dob.getText().toString(),password.getText().toString(),nic.getText().toString(),age.getText().toString(),gender);
                if(status){
                    Toast.makeText(EdiPro.this, "Driver Updated !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(EdiPro.this, "Update Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //delete driver profile
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBRHandler dbrHandler = new DBRHandler(getApplicationContext());
                dbrHandler.deleteInfo(drivername.getText().toString());

                Toast.makeText(EdiPro.this, "Driver Deleted !", Toast.LENGTH_SHORT).show();
                drivername.setText(null);
                dob.setText(null);
                password.setText(null);
                nic.setText(null);
                age.setText(null);
                male.setChecked(false);
                female.setChecked(false);
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

    }
}
