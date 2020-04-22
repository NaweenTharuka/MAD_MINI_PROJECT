package com.example.broomlk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//add vehicle to system
public class AddVehicle extends AppCompatActivity {
    private static final String CHANNEL_ID = "project" ;
    VehicleDatabase mydb;
    EditText editvtype,editoname,editonic,editvnumber,editengnum;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        mydb = new VehicleDatabase(this); //call for vehicleDatabase

        editvtype = (EditText)findViewById(R.id.et7); //cast vehicle type
        editoname = (EditText)findViewById(R.id.et8); //cast Owner name
        editonic = (EditText)findViewById(R.id.et9); //cast owner NIC
        editvnumber = (EditText)findViewById(R.id.et10); //cast vehicle Number
        editengnum = (EditText)findViewById(R.id.et11); //cast Engine Number

        addbtn = (Button)findViewById(R.id.Btn4); //cast Add button
        Adddata(); //call for Adddata() method


        //create notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "text";
            String description = "text";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        
    }

    //adding data
    public void Adddata(){
        addbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {   //data validations
                        if(TextUtils.isEmpty(editvtype.getText())){ //validate vehicle type is empty
                            editvtype.setError("Enter Vehicle type !");
                            editvtype.requestFocus(); }
                        else if(TextUtils.isEmpty(editoname.getText())){ //validate Owner name is empty
                            editoname.setError("Enter Owner Name !");
                            editoname.requestFocus(); }
                        else if(TextUtils.isEmpty(editonic.getText())){ //validate Owner NIC is empty
                            editonic.setError("Enter Owner NIC !");
                            editonic.requestFocus(); }
                        else if(TextUtils.isEmpty(editvnumber.getText())){ //validate vehicle number is empty
                            editvnumber.setError("Enter Vehicle Number");
                            editvnumber.requestFocus(); }
                        else if(TextUtils.isEmpty(editengnum.getText())){ //validate vehicle engine number is empty
                            editengnum.setError("Enter Vehicle Engine Number !");
                            editengnum.requestFocus(); }
                        else{ //parse data to inserData function in DB
                            boolean isInsereted = mydb.inserData(editvtype.getText().toString(),
                                    editoname.getText().toString(),
                                    editonic.getText().toString(),
                                    editvnumber.getText().toString(),
                                    editengnum.getText().toString());
                            if(isInsereted = true){
                                Toast.makeText(AddVehicle.this, "Inserted New Vehicle !!", Toast.LENGTH_LONG).show();//success toast message
                                addNotification();} //call notification function
                            else
                                Toast.makeText(AddVehicle.this, "Data not Inserted !!", Toast.LENGTH_LONG).show(); //unsuccess toast message

                        }
                    }
                }

        );
    }

    //notification function
    public void addNotification(){
        String msg = editoname.getText().toString();

        Intent intent = new Intent(this, AdminView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Register")
                .setContentText("Success!! "+ msg + "'s vehicle is added to Broom.lk") //notification message with owner name
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }
}
