package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Button go = (Button) findViewById(R.id.btnMain);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(FirstPage.this, MainActivity.class);
                startActivity(continueIntent);
            }
        });
    }
}
