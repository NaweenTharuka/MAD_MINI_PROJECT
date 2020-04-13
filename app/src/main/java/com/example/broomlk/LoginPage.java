package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        Button adminbtn = (Button) findViewById(R.id.button8);


        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(LoginPage.this, Adminpanel.class);
                startActivity(continueIntent);
            }
        });
    }
}
