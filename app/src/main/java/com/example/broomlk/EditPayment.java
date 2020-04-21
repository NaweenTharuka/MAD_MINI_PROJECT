package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EditPayment extends AppCompatActivity {

    EditText holdername, cardnum, exdate,cvvnum ;
    Button edit, delete, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        holdername = findViewById(R.id.edholder);
        cardnum = findViewById(R.id.edcnum);
        exdate = findViewById(R.id.eddate);
        cvvnum =  findViewById(R.id.edcv);
        edit = findViewById(R.id.edpbtn);
        delete = findViewById(R.id.pedbtn);
        search = findViewById(R.id.edpsearch);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                List hname = pdbHandler.readAllInfo(holdername.getText().toString());

                if (hname.isEmpty()){
                    Toast.makeText(EditPayment.this, "No Details", Toast.LENGTH_SHORT).show();
                    holdername.setText(null);
                }
                else {

                    Toast.makeText(EditPayment.this, "Details Found! Details: "+hname.get(0).toString(), Toast.LENGTH_SHORT).show();
                    holdername.setText(hname.get(0).toString());
                    cardnum.setText(hname.get(1).toString());
                    exdate.setText(hname.get(2).toString());
                    cvvnum.setText(hname.get(3).toString());



                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());

                Boolean status = pdbHandler.updateInfo(holdername.getText().toString(),cardnum.getText().toString(),exdate.getText().toString(),cvvnum.getText().toString());
                if (status){
                    Toast.makeText(EditPayment.this, "Details Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditPayment.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PDBHandler pdbHandler = new PDBHandler(getApplicationContext());
                pdbHandler.deleteInfo(holdername.getText().toString());

                Toast.makeText(EditPayment.this, "Payment Details Deleted", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),ResConfirmation.class);
                startActivity(i);

                holdername.setText(null);
                cardnum.setText(null);
                exdate.setText(null);
                cvvnum.setText(null);
            }
        });
    }
}
