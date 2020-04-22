package com.example.broomlk;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBRHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBRHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DriverProfile.Driver.TABLE_NAME + " (" +
                    DriverProfile.Driver._ID + " INTEGER PRIMARY KEY," +
                    DriverProfile.Driver.COLUMN_1 + " TEXT," +
                    DriverProfile.Driver.COLUMN_2 + " TEXT," +
                    DriverProfile.Driver.COLUMN_3 + " TEXT," +
                    DriverProfile.Driver.COLUMN_4 + " TEXT," +
                    DriverProfile.Driver.COLUMN_5 + " TEXT," +
                    DriverProfile.Driver.COLUMN_6 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DriverProfile.Driver.TABLE_NAME;

    //ADD INFO
    public long addInfo (String username, String dob, String password,String nic,String age, String gender){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(DriverProfile.Driver.COLUMN_1, username);
        values.put(DriverProfile.Driver.COLUMN_2, dob);
        values.put(DriverProfile.Driver.COLUMN_3, password);
        values.put(DriverProfile.Driver.COLUMN_4, nic);
        values.put(DriverProfile.Driver.COLUMN_5, age);
        values.put(DriverProfile.Driver.COLUMN_6, gender);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DriverProfile.Driver.TABLE_NAME, null, values);

        return newRowId;

    }

    //UPDATE DRIVER
    public Boolean updateInfo (String username, String dob, String password,String nic,String age, String gender){

        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DriverProfile.Driver.COLUMN_2, dob);
        values.put(DriverProfile.Driver.COLUMN_3, password);
        values.put(DriverProfile.Driver.COLUMN_4, nic);
        values.put(DriverProfile.Driver.COLUMN_5, age);
        values.put(DriverProfile.Driver.COLUMN_6, gender);

        // Which row to update, based on the title
        String selection = DriverProfile.Driver.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                DriverProfile.Driver.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1 ) {
            return true;
        }
        else {
            return false;
        }

    }

    //DELETE INFO
    public void deleteInfo (String username){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = DriverProfile.Driver.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { username };
        // Issue SQL statement.
        int deletedRows = db.delete(DriverProfile.Driver.TABLE_NAME, selection, selectionArgs);


    }

    //READ
    public ArrayList readAllInfo (){

        String username = "nimesh";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DriverProfile.Driver.COLUMN_1,
                DriverProfile.Driver.COLUMN_2,
                DriverProfile.Driver.COLUMN_3,
                DriverProfile.Driver.COLUMN_4,
                DriverProfile.Driver.COLUMN_5,
                DriverProfile.Driver.COLUMN_6
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DriverProfile.Driver.COLUMN_1 + " = ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DriverProfile.Driver.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                DriverProfile.Driver.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_1));
            usernames.add(user);
        }
        cursor.close();
        return usernames;
    }

    ///////////////////////////////////////////////
    public List readAllInfo (String username){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DriverProfile.Driver.COLUMN_1,
                DriverProfile.Driver.COLUMN_2,
                DriverProfile.Driver.COLUMN_3,
                DriverProfile.Driver.COLUMN_4,
                DriverProfile.Driver.COLUMN_5,
                DriverProfile.Driver.COLUMN_6
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DriverProfile.Driver.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DriverProfile.Driver.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                DriverProfile.Driver.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_1));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_2));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_3));
            String nic = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_4));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_5));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_6));
            userInfo.add(user);//0
            userInfo.add(dob);//1
            userInfo.add(pass);//2
            userInfo.add(nic);//3
            userInfo.add(age);//4
            userInfo.add(gender);//5
        }
        cursor.close();
        return userInfo;
    }

    //LOGIN
    public Boolean LoginUser(String username,String password){

        SQLiteDatabase db = getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DriverProfile.Driver.COLUMN_1,
                DriverProfile.Driver.COLUMN_3
        };

    // Filter results WHERE "title" = 'My Title'
        String selection = DriverProfile.Driver.COLUMN_1 + " = ? AND "+DriverProfile.Driver.COLUMN_3 +"= ?";
        String[] selectionArgs = { username,password};

    // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DriverProfile.Driver.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                DriverProfile.Driver.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        String validUser = null;
        while(cursor.moveToNext()){
            validUser = cursor.getString(cursor.getColumnIndexOrThrow(DriverProfile.Driver.COLUMN_1));

        }
        cursor.close();
        if(validUser==null){
            return false;
        }
        else{
            return true;
        }
    }


}