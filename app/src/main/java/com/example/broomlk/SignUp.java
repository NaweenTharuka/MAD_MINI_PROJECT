package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//signup data
public class SignUp extends AppCompatActivity {

    EditText username, userFName,userLName,userEmail,phone, password,city ;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.usertxt); //cast user name
        userFName = findViewById(R.id.Fname); //cast user first name
        userLName = findViewById(R.id.Lname); //cast user last name
        userEmail = findViewById(R.id.emailInput); //cast user Email
        phone = findViewById(R.id.editText4); //cast phone number
        password = findViewById(R.id.editText5); //cast password
        city = findViewById(R.id.cityInput); //cast city
        submit= findViewById(R.id.btnsub); //cast submit button



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText())){
                    username.setError("Please Enter User Name"); //validate all data are inserted
                    username.requestFocus();
                }
                else if(TextUtils.isEmpty(userFName.getText())){
                    userFName.setError("Please Enter First Name");
                    userFName.requestFocus();
                }
                else if(TextUtils.isEmpty(userLName.getText())){
                    userLName.setError("Please Enter Last Name");
                    userLName.requestFocus();
                }
                else if(TextUtils.isEmpty(userEmail.getText())){
                    userEmail.setError("Please Enter Email");
                    userEmail.requestFocus();
                }
                else if(TextUtils.isEmpty(phone.getText())){
                    phone.setError("Please Enter Phone Number");
                    phone.requestFocus();
                }
                else if(TextUtils.isEmpty(password.getText())){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                }
                else if(TextUtils.isEmpty(city.getText())){
                    city.setError("Please Enter City");
                    city.requestFocus();
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
