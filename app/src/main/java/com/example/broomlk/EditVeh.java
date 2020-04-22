package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Edit vehicle Details
public class EditVeh extends AppCompatActivity {
    VehicleDatabase mydb3;
    EditText Id,editVtype,editOname,editOnic,editVnumber,editEngnum;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_veh);

        mydb3 = new VehicleDatabase(this); //call for VehicleDatabase

        editVtype = (EditText)findViewById(R.id.editText8); //cast vehicle type
        editOname = (EditText)findViewById(R.id.editText9); //cast owner name
        editOnic = (EditText)findViewById(R.id.editText10); //cast owner NIC
        editVnumber = (EditText)findViewById(R.id.editText11); //cast vehicle
        editEngnum = (EditText)findViewById(R.id.editText12); //cast vehicle engine number
        Id = (EditText)findViewById(R.id.editText7); //cast edit vehicle ID

        editBtn = (Button)findViewById(R.id.Btn4); //cast edit button
        UpdateData(); //call for updateData method

    }

    // method for update data
    public  void UpdateData(){
        editBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //get edited data one by one and parse to DB
                        if(TextUtils.isEmpty(editVtype.getText())){
                            editVtype.setError("Enter Vehicle type !");
                            editVtype.requestFocus(); }
                        else if(TextUtils.isEmpty(editOname.getText())){
                            editOname.setError("Enter Owner Name !");
                            editOname.requestFocus(); }
                        else if(TextUtils.isEmpty(editOnic.getText())){
                            editOnic.setError("Enter Owner NIC !");
                            editOnic.requestFocus(); }
                        else if(TextUtils.isEmpty(editVtype.getText())){
                            editVtype.setError("Enter Vehicle Number");
                            editVtype.requestFocus(); }
                        else if(TextUtils.isEmpty(editEngnum.getText())){
                            editEngnum.setError("Enter Vehicle Engine Number !");
                            editEngnum.requestFocus(); }
                        else{
                            boolean isUpdated = mydb3.updateData(Id.getText().toString(),editVtype.getText().toString(),editOname.getText().toString(),editOnic.getText().toString(),editVnumber.getText().toString(),editEngnum.getText().toString());
                            if(isUpdated == true)
                                Toast.makeText(EditVeh.this, "Data Updated!!", Toast.LENGTH_SHORT).show(); //success toast message
                            else
                                Toast.makeText(EditVeh.this, "Data is not Updated!!", Toast.LENGTH_SHORT).show(); //unsuccess toast message

                        }
                    }
                }
        );
    }
}
