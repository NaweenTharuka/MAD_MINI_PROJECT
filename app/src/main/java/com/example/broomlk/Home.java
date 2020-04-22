package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    EditText username,password;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button);
        register = findViewById(R.id.button2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DriverProfiles.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBRHandler dbrHandler = new DBRHandler(getApplicationContext());
                if(dbrHandler.LoginUser(username.getText().toString(),password.getText().toString())){
                    Toast.makeText(Home.this, "Logged in Success !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),DriverList.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Home.this, "No valid User !", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}

