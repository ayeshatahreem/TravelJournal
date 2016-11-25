package com.ayesha.hp.traveljournal;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.vision.text.Text;

public class AddJournalEntry extends AppCompatActivity {

    Calendar calendar;
    String in_date;
    double latitude;
    double longitude;

    Boolean location_added = false;
    Boolean Title_added = false;

    String coverPath;

    EditText mtitle;
    EditText mDescription;

    Toolbar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal_entry);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Add new entry");


        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        mtitle = (EditText) findViewById(R.id.in_title);
        mDescription = (EditText) findViewById(R.id.in_description);

        calendar = Calendar.getInstance();

        TextView text = (TextView)findViewById(R.id.Current_Date);

        text.setText(calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH) + "/"+ calendar.get(Calendar.YEAR));
    }

    public void PickLocation(View view) throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        int PLACE_PICKER_REQUEST = 1;

        Toast.makeText(this, "Entering the place picker", Toast.LENGTH_SHORT);
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
    }

    public void PickImage(View view) {
        int RESULT_LOAD_IMG = 2;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int PLACE_PICKER_REQUEST = 1;
        int RESULT_LOAD_IMG = 2;
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;

                location_added = true;

                TextView text = (TextView) findViewById(R.id.in_location);
                text.setText(String.valueOf(longitude) +" "+ String.valueOf(latitude));
            }
        } else if (requestCode == RESULT_LOAD_IMG) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri selectedImage = data.getData();
                    TextView txtView = (TextView) findViewById(R.id.image_name);
                    // Set the Image login ImageView after decoding the String
                    txtView.setText(selectedImage.getEncodedPath());
                    coverPath = selectedImage.toString();
                }
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pickDate(View view) {
        calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                in_date = String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/" + String.valueOf(year);
                test(in_date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        dialog.show();
    }

    public void test(String in)
    {
        Toast.makeText(this, in, Toast.LENGTH_SHORT).show();
        TextView text = (TextView)findViewById(R.id.Current_Date);
        text.setText(in);
    }

    public void saveEntry(View view) {

        if(!location_added)
        {
            Toast.makeText(this, "Please pick a location for your entry.", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(mtitle.getText().toString() == "")
            {
                Toast.makeText(this, "Please enter a title for your entry", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String entryTitle = mtitle.getText().toString();
                String description = mDescription.getText().toString();

               JournalEntryDatabaseAdapter adapter = new JournalEntryDatabaseAdapter(this);
                in_date = ((TextView)findViewById(R.id.Current_Date)).getText().toString();

                int id = adapter.insert(entryTitle, description, in_date, coverPath, longitude, latitude);

                Toast.makeText(this, String.valueOf(id)+" added to database", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Entry Added");
                alertDialogBuilder.setMessage("The entry with id "+String.valueOf(id)+" has been added to database.");
                alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        });
                alertDialogBuilder.show();
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
