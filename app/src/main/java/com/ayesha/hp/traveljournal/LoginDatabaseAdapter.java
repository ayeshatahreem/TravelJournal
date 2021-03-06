package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 16/11/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDatabaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table "+"LOGIN"+ "( " +"ID"+" integer primary key autoincrement,"+
            "USERNAME text,PASSWORD text); ";
    public SQLiteDatabase db;
    private final Context context;
    private LoginDatabaseHelper dbHelper;
    public LoginDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new LoginDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public LoginDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName,String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);
        db.insert("LOGIN", null, newValues);
    }
    public int deleteEntry(String UserName) {
        String where="USERNAME=?";
        int numOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        return numOFEntriesDeleted;
    }
    public String getSingleEntry(String userName) {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName does not exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public void updateEntry(String userName,String password) {
        ContentValues update = new ContentValues();
        update.put("USERNAME", userName);
        update.put("PASSWORD",password);
        String where="USERNAME = ?";
        db.update("LOGIN",update, where, new String[]{userName});
    }
}