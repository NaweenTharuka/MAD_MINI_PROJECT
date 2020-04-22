package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//view drivers
public class DriverList extends AppCompatActivity {

    ListView userList;
    ArrayList dataList;
    ArrayAdapter adapter;

    DBRHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);

        userList = findViewById(R.id.users_list);

        db = new DBRHandler(getApplicationContext());
        dataList = db.readAllInfo();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);
        userList.setAdapter(adapter);

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                String text = userList.getItemAtPosition(i).toString();
                Toast.makeText(DriverList.this, "User :"+text, Toast.LENGTH_SHORT).show();
                return true;
            }

        });
    }
}
