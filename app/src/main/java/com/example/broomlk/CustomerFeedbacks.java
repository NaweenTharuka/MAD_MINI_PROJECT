package com.example.broomlk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//Customer feedback
public class CustomerFeedbacks extends AppCompatActivity {
    FeedbacksDatabase fedDb;
    private Spinner sp3, sp4;
    Button fAdd,fView,fEdit,fDel;
    EditText other,editId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedbacks);
        fedDb = new FeedbacksDatabase(this);// call for data base
        sp3 = (Spinner) findViewById(R.id.spinner3); //cast C satisfaction
        sp4 = (Spinner) findViewById(R.id.spinner4); //cast C rating
        other = (EditText)findViewById(R.id.fedOther); //cast other msg

        Button skip = (Button) findViewById(R.id.fedSkip); //cast Skip button
        fAdd = (Button) findViewById(R.id.fedAdd); //cast Add feedback button
        fView = (Button) findViewById(R.id.fedView); //cast View feedback button
        fEdit = (Button) findViewById(R.id.fededitbtn); //cast Edit feedback button
        fDel = (Button) findViewById(R.id.fedDel); //cast delete feedback button
        editId= (EditText)findViewById(R.id.fededit); //cast Edit feedback button

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(CustomerFeedbacks.this, LastPage.class);
                startActivity(continueIntent);
            }
        });

        //array for satisfaction dropdown values
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CustomerFeedbacks.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.feedback));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(myAdapter);

        //array for rating dropdown values
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(CustomerFeedbacks.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.rating));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(myAdapter1);

    }

    //add data method
    public void AddData(){
        fAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //calling for insertData method
                        boolean isInserted = fedDb.inserData(sp3.getSelectedItem().toString(),
                                        sp4.getSelectedItem().toString(),
                                        other.getText().toString());

                        if(isInserted = true)
                            Toast.makeText(CustomerFeedbacks.this, "New Record Inserted !", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CustomerFeedbacks.this, "Data not Inserted !", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    //View All data method
    public void ViewAll(){
        fView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = fedDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error!", "There is no Feedback");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Feedback :"+ res.getString(1)+"\n");
                            buffer.append("Rating :"+ res.getString(2)+"\n");
                            buffer.append("Other :"+ res.getString(3)+"\n\n");
                        }

                        showMessage("Feedback",buffer.toString());
                    }
                }
        );
    }
    //method for show message
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    //Update data method
    public void UpdateData(){
        fEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = fedDb.updateData(editId.getText().toString(),sp3.getSelectedItem().toString(),sp4.getSelectedItem().toString(),other.getText().toString());
                        if(isUpdated == true)
                            Toast.makeText(CustomerFeedbacks.this, "Your Feedback Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CustomerFeedbacks.this, "Data not Updated!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    //Delete data method
    public void DeleteData(){
        fDel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = fedDb.deleteData(editId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(CustomerFeedbacks.this, "Your feedback deleted!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CustomerFeedbacks.this, "Data not Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
