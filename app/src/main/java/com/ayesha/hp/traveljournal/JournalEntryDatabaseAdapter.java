package com.ayesha.hp.traveljournal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;

import java.util.Date;

/**
 * Created by Shaoor Munir on 19-Nov-16.
 */

public class JournalEntryDatabaseAdapter {
    private static String DATABASE_NAME = "entries.db";

    private static String TABLE_NAME = "JournalEntries";
    private static String ENTRY_ID = "entryID";
    private static String TITLE = "title";
    private static String DESCRIPTION = "description";
    private static String DATE = "entryDate";
    private static String COVER = "coverImagePath";
    private static String LONGITUDE = "longitude";
    private static String LATITUDE = "latitude";

    static final String DB_CREATE = "create table " + TABLE_NAME +" (" +
            ENTRY_ID + " integer primary key autoincrement,"+
            TITLE+" text,"+
            DESCRIPTION+" text,"+
            DATE+" text,"+
            COVER+" text,"+
            LONGITUDE+" double,"+
            LATITUDE+" double" + ")";

    private SQLiteDatabase db;

    private Context context;


    JournalEntryDatabaseAdapter(Context context)
    {
        this.context = context;
        JournalEntryDatabaseHelper dbHelper = new JournalEntryDatabaseHelper(context, DATABASE_NAME, null, 1);

        db = dbHelper.getWritableDatabase();
    }

    public int insert(String title, String description, String inDate, String coverPath, double longitude, double latitude)
    {
        ContentValues value = new ContentValues();

        value.put(TITLE, title);
        value.put(DESCRIPTION, description);
        value.put(DATE, inDate);
        value.put(COVER, coverPath);
        value.put(LONGITUDE, longitude);
        value.put(LATITUDE, latitude);

        return (int) db.insert(TABLE_NAME, null, value);
    }
    public Cursor getAll()
    {
        return db.rawQuery("select * from "+TABLE_NAME, null);
    }
    public Cursor getSingleRow(int id)
    {
        return db.rawQuery("select * from "+TABLE_NAME+" where "+ENTRY_ID+"=?",  new String[]{id+""});
    }
}
