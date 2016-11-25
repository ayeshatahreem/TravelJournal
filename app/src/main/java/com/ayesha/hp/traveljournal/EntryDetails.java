package com.ayesha.hp.traveljournal;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EntryDetails extends AppCompatActivity {

    JournalEntryDatabaseAdapter adapter;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_details);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        adapter = new JournalEntryDatabaseAdapter(this);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();


        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        int id = 0;
        id = getIntent().getIntExtra("id", 0);

        Cursor cursor = adapter.getSingleRow(id);


        if (cursor != null) {
            if (cursor.moveToFirst()) {

                actionBar.setTitle(cursor.getString(cursor.getColumnIndex("entryDate")));
                TextView title = (TextView)findViewById(R.id.entry_title);
                title.setText(cursor.getString(cursor.getColumnIndex("title")));

                Uri temp = Uri.parse(cursor.getString(cursor.getColumnIndex("coverImagePath")));
                Bitmap myBitmap = BitmapFactory.decodeFile(temp.getPath());

                ImageView cover = (ImageView)findViewById(R.id.entry_coverImage);

                cover.setImageBitmap(myBitmap);


                TextView description = (TextView)findViewById(R.id.entry_description);

                description.setText(cursor.getString(cursor.getColumnIndex("description")));

            }
        }




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
