package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteVeh extends AppCompatActivity {
    VehicleDatabase mydb2;
    EditText eId;
    Button delbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_veh);

        mydb2 = new VehicleDatabase(this);
        eId = (EditText)findViewById(R.id.et13);
        delbtn = (Button) findViewById(R.id.delbtn);

        DeleteData();

    }

    public void DeleteData(){
        delbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = mydb2.deleteData(eId.getText().toString());
                        if(deleteRows > 0)
                            Toast.makeText(DeleteVeh.this, "Data Deleted !!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DeleteVeh.this, "Data not Deleted !!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
