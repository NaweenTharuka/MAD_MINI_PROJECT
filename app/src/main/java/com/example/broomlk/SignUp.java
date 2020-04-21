package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText username, userFName,userLName,userEmail,phone, password,city ;
    Button submit, updateProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.usertxt);
        userFName = findViewById(R.id.Fname);
        userLName = findViewById(R.id.Lname);
        userEmail = findViewById(R.id.emailInput);
        phone = findViewById(R.id.editText4);
        password = findViewById(R.id.editText5);
        city = findViewById(R.id.cityInput);
        submit= findViewById(R.id.btnsub);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText())){
                    username.setError("Please Enter User Name");
                    username.requestFocus();
                }else {
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addInfo(username.getText().toString(), userFName.getText().toString(), userLName.getText().toString(), userEmail.getText().toString(), phone.getText().toString(), password.getText().toString(), city.getText().toString());
                    Toast.makeText(SignUp.this, "User Added. User ID: " + newID, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(i);
                    username.setText(null);
                    userFName.setText(null);
                    userLName.setText(null);
                    userEmail.setText(null);
                    phone.setText(null);
                    password.setText(null);
                    password.setText(null);
                    city.setText(null);
                }



            }

        });


    }


}
