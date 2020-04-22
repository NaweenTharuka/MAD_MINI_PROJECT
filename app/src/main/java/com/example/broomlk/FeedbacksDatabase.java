package com.example.broomlk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Customer Feedback database
public class FeedbacksDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Feedback.db"; //define database name
    public static final String TABLE_NAME = "feedback"; //define table name
    public static final String COL_1 = "ID"; //define column 1: feedback ID
    public static final String COL_2 = "FEEDBACK"; //define column 2: Customer satisfaction
    public static final String COL_3 = "RATE"; //define column 3: customer rating
    public static final String COL_4 = "OTHER"; //define column 4: Other msg

    //constructor for create table & Database
    public FeedbacksDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FEEDBACK TEXT,RATE TEXT,OTHER TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //insert data method
    public boolean inserData(String feedback,String rating,String other){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,feedback);
        contentValues.put(COL_3,rating);
        contentValues.put(COL_4,other);
        Long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //View All data method
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    //update data method
    public boolean updateData(String id,String feedback,String rating,String other){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,feedback);
        contentValues.put(COL_3,rating);
        contentValues.put(COL_4,other);
        db.update(TABLE_NAME, contentValues,"id = ?",new String[] { id });
        return true;
    }

    //delete data method
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] { id });
    }
}
