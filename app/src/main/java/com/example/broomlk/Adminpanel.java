package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//admin panel main homepage
//admin can select driver or vehicle registration
public class Adminpanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);

        Button adddriver = (Button) findViewById(R.id.driver); //cast Driver Registration button
        Button addvehicle = (Button) findViewById(R.id.vehicle); //cast Vehicle Registration button

        // link to vehicle registration Home Page
        addvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Adminpanel.this, AdminView.class);
                startActivity(continueIntent);
            }
        });

        // link to Driver Registration Home Page
        adddriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Adminpanel.this,Home.class);
                startActivity(continueIntent);
            }
        });


    }
}
