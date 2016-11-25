package com.ayesha.hp.traveljournal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EntriesListAdapter extends BaseAdapter {

    private ArrayList<DiaryEntry> entries;
    private Context context;
    private JournalEntryDatabaseAdapter adapter;
    ArrayList<Bitmap>images;
    EntriesListAdapter(Context context) {
        this.context = context;
        adapter = new JournalEntryDatabaseAdapter(context);
        Cursor cursor = adapter.getAll();
        entries = new ArrayList<DiaryEntry>();
        images = new ArrayList<Bitmap>();
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
                    Uri temp = Uri.parse(imagepath);
                    entries.add(new DiaryEntry(id, title, description, temp.getPath(), entryDate, latitude, longitude));
                } while (cursor.moveToNext());
            }

            for (int i = 0; i < entries.size(); i++)
            {

                File file = new File(entries.get(i).coverPath);
                images.add(i, decodeFile(file));
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
        TextView description = (TextView) convertView.findViewById(R.id.entry_date);
        ImageView image = (ImageView) convertView.findViewById(R.id.cover_image);
        title.setText(entry.title);
        description.setText(entry.entryDate);
        image.setImageBitmap(images.get(position));
        return convertView;
    }

    public void openDetails(int position)
    {
        Intent intent = new Intent(context, EntryDetails.class);
        intent.putExtra("id", entries.get(position).id);
        context.startActivity(intent);
    }
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=100;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
}
