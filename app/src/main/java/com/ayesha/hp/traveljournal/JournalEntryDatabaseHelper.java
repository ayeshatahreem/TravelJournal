package com.ayesha.hp.traveljournal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shaoor Munir on 19-Nov-16.
 */

public class JournalEntryDatabaseHelper extends SQLiteOpenHelper {
    public JournalEntryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(JournalEntryDatabaseAdapter.DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
