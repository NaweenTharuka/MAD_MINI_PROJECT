package com.example.broomlk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Vehicle Database
public class VehicleDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Vehicle.db"; //define Database Name
    public static final String TABLE_NAME = "vehicle_table"; //define Table Name
    public static final String COL_1 = "ID"; //define column 1: ID
    public static final String COL_2 = "VTYPE"; //define column 2: vehicle type
    public static final String COL_3 = "ONAME"; //define column 3: owner name
    public static final String COL_4 = "ONIC"; //define column 4: Owner NIC
    public static final String COL_5 = "VNUMBER"; //define column 5: Vehicle Number
    public static final String COL_6 = "VENGNUM"; //define column 6: Vehicle engine Number

    public VehicleDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,VTYPE TEXT,ONAME TEXT,ONIC TEXT,VNUMBER TEXT,VENGNUM TEXT)");//create table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    //method for insert Data
    public boolean inserData(String vtype,String oname,String onic,String vnumber,String vengnum){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,vtype);
        contentValues.put(COL_3,oname);
        contentValues.put(COL_4,onic);
        contentValues.put(COL_5,vnumber);
        contentValues.put(COL_6,vengnum);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //method for view All data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }

    //method for Update data
    public boolean updateData(String id,String vtype,String oname,String onic,String vnumber,String vengnum){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,vtype);
        contentValues.put(COL_3,oname);
        contentValues.put(COL_4,onic);
        contentValues.put(COL_5,vnumber);
        contentValues.put(COL_6,vengnum);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;

    }

    //method for delete data
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] { id });
    }
}
