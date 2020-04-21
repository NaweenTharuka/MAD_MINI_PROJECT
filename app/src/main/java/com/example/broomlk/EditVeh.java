package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditVeh extends AppCompatActivity {
    VehicleDatabase mydb3;
    EditText Id,editVtype,editOname,editOnic,editVnumber,editEngnum;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_veh);

        mydb3 = new VehicleDatabase(this);

        editVtype = (EditText)findViewById(R.id.editText8);
        editOname = (EditText)findViewById(R.id.editText9);
        editOnic = (EditText)findViewById(R.id.editText10);
        editVnumber = (EditText)findViewById(R.id.editText11);
        editEngnum = (EditText)findViewById(R.id.editText12);
        Id = (EditText)findViewById(R.id.editText7);

        editBtn = (Button)findViewById(R.id.Btn4);
        UpdateData();

    }
    public  void UpdateData(){
        editBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                                Toast.makeText(EditVeh.this, "Data Updated!!", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(EditVeh.this, "Data is not Updated!!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        );
    }
}
