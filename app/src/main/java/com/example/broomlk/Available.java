package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Available extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        Button book1 = (Button) findViewById(R.id.book1);
        Button book2 = (Button) findViewById(R.id.book2);
        Button book3 = (Button) findViewById(R.id.book3);
        Button book4 = (Button) findViewById(R.id.book4);
        Button book5 = (Button) findViewById(R.id.book5);
        Button book6 = (Button) findViewById(R.id.book6);
        Button book7 = (Button) findViewById(R.id.book7);
        Button book8 = (Button) findViewById(R.id.book8);

        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });

        book8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });
    }
}
