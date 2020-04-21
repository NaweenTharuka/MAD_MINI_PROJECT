package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText username,password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        username = findViewById(R.id.lusername);
        password = findViewById(R.id.lpassword);
        login = findViewById(R.id.lbtn);

        TextView frpassword = (TextView) findViewById(R.id.fpassword);

        frpassword .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(LoginPage.this, EditUser.class);
                startActivity(continueIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(LoginPage.this, Adminpanel.class);
                startActivity(continueIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                if(dbHandler.LoginUser(username.getText().toString(),password.getText().toString())){
                    Toast.makeText(LoginPage.this, "Logged in Success !", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                    password.setText(null);
                    Intent i = new Intent(getApplicationContext(),Available.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginPage.this, "No valid User !", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
