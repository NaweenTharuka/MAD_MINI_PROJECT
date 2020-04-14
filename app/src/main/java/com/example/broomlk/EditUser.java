package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static com.example.broomlk.R.id.fnameinput;

public class EditUser extends AppCompatActivity {

    EditText username, userFName,userLName,userEmail,phone, password,city ;
    Button delete, submit, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        username = findViewById(R.id.eduser);
        userFName = findViewById(fnameinput);
        userLName = findViewById(R.id.Lname);
        userEmail = findViewById(R.id.emailInput);
        phone = findViewById(R.id.phoneinput);
        password = findViewById(R.id.passwordinput);
        city = findViewById(R.id.cityInput);
        delete = findViewById(R.id.d65btn);
        submit= findViewById(R.id.buttonsub);
        search = findViewById(R.id.edsearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(username.getText().toString());

                if (user.isEmpty()){
                    Toast.makeText(EditUser.this, "No User", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }
                else {

                    Toast.makeText(EditUser.this, "User Found! User: "+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    username.setText(user.get(0).toString());
                    userFName.setText(user.get(1).toString());
                    userLName.setText(user.get(2).toString());
                    userEmail.setText(user.get(3).toString());
                    phone.setText(user.get(4).toString());
                    password.setText(user.get(5).toString());
                    city.setText(user.get(6).toString());

                }

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Boolean status = dbHandler.updateInfo(username.getText().toString(),userFName.getText().toString(),userLName.getText().toString(),userEmail.getText().toString(),phone.getText().toString(),password.getText().toString(),city.getText().toString());
                if (status){
                    Toast.makeText(EditUser.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditUser.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(i);

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(username.getText().toString());

                Toast.makeText(EditUser.this, "User Deleted", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);

                username.setText(null);
                userFName.setText(null);
                userLName.setText(null);
                userEmail.setText(null);
                userFName.setText(null);
                phone.setText(null);
                password.setText(null);
                city.setText(null);

            }
        });







    }
}