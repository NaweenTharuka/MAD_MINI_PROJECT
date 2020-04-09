package com.example.broomlk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminView extends AppCompatActivity {
    VehicleDatabase mydb1;
    Button viewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        mydb1 = new VehicleDatabase(this);
        viewbtn = (Button)findViewById(R.id.btn6);
        ViewAll();

        Button addbtn = (Button) findViewById(R.id.Btn4);
        Button editbtn = (Button)findViewById(R.id.btn5);
        Button delbtn = (Button)findViewById(R.id.btn7);
        Button viewbtn = (Button)findViewById(R.id.btn6);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(AdminView.this,AddVehicle.class);
                startActivity(continueIntent);
            }
        });
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(AdminView.this,EditVeh.class);
                startActivity(continueIntent);
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(AdminView.this,DeleteVeh.class);
                startActivity(continueIntent);
            }
        });
    }

    public void ViewAll(){
        viewbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb1.getAllData();
                        if(res.getCount()==0){
                            ShowMessage("Error!!","Nothing Found!!");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0 )+ "\n" );
                            buffer.append("Veh Type :"+ res.getString(1 )+ "\n" );
                            buffer.append("Owner Name :"+ res.getString(2 )+ "\n" );
                            buffer.append("Owner NIC :"+ res.getString(3 )+ "\n" );
                            buffer.append("Veh No :"+ res.getString(4 )+ "\n" );
                            buffer.append("Veh Engine No :"+ res.getString(5 )+ "\n\n" );
                        }
                        ShowMessage("Vehicle Details",buffer.toString());
                    }
                }
        );
    }

    public  void ShowMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

