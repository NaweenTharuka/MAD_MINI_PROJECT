package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Delete Vehicle Page
public class DeleteVeh extends AppCompatActivity {
    VehicleDatabase mydb2;
    EditText eId;
    Button delbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_veh);

        mydb2 = new VehicleDatabase(this); //call for VehicleDatabase
        eId = (EditText)findViewById(R.id.et13); // cast delete vehicle ID
        delbtn = (Button) findViewById(R.id.delbtn); //cast delete vehicle button

        DeleteData(); //call for DeleteData method

    }

    //method for delete vehicle
    public void DeleteData(){
        delbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = mydb2.deleteData(eId.getText().toString()); // parse delete vehicle ID to DB
                        if(deleteRows > 0) //check whether all rows are deleted or not
                            Toast.makeText(DeleteVeh.this, "Data Deleted !!", Toast.LENGTH_SHORT).show(); //show success toast message
                        else
                            Toast.makeText(DeleteVeh.this, "Data not Deleted !!", Toast.LENGTH_SHORT).show(); //show unsuccess toast message
                    }
                }
        );
    }
}
