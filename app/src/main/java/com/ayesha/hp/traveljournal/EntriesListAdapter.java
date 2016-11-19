package com.ayesha.hp.traveljournal;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Shaoor Munir on 19-Nov-16.
 */

public class EntriesListAdapter extends BaseAdapter {

    private class DiaryEntry {
        int id;
        String title;
        String description;
        String coverPath;
        String entryDate;
        double latitude;
        double longitude;

        DiaryEntry(int id, String title, String description, String coverPath, String entryDate, double latitude, double longitude) {
            this.id = id;
            this.coverPath = coverPath;
            this.title = title;
            this.description = description;
            this.entryDate = entryDate;
            this.latitude = latitude;
            this.longitude = longitude;
            this.coverPath = coverPath;
        }
    }

    private ArrayList<DiaryEntry> entries;
    private Context context;
    private JournalEntryDatabaseAdapter adapter;

    EntriesListAdapter(Context context) {
        this.context = context;

        adapter = new JournalEntryDatabaseAdapter(context);

        Cursor cursor = adapter.getAll();

        entries = new ArrayList<DiaryEntry>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("entryID"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String description = cursor.getString(cursor.getColumnIndex("description"));
                    String imagepath = cursor.getString(cursor.getColumnIndex("coverImagePath"));
                    String entryDate = cursor.getString(cursor.getColumnIndex("entryDate"));
                    double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                    double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));

                    entries.add(new DiaryEntry(id, title, description, imagepath, entryDate, latitude, longitude));
                } while (cursor.moveToNext());
            }
        }
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.diary_entries_layout, parent, false);
        }


        DiaryEntry entry = (DiaryEntry) getItem(position);
        TextView title = (TextView) convertView.findViewById(R.id.title_entry);
        TextView description = (TextView) convertView.findViewById(R.id.entry_description);


        title.setText(entry.title);
        description.setText(entry.description);
        ImageView image = (ImageView) convertView.findViewById(R.id.cover_image);
        Bitmap myBitmap = BitmapFactory.decodeFile(entry.coverPath);
        image.setImageBitmap(myBitmap);

        return convertView;
    }
}
