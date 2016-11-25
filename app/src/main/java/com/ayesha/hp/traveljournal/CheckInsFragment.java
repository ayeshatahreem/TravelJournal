package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 29/10/16.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import java.util.ArrayList;


public class CheckInsFragment extends Fragment {

    private ArrayList<DiaryEntry> entries = new ArrayList<DiaryEntry>();
    private Context context;
    private JournalEntryDatabaseAdapter adapter;

    MapView mMapView;
    private GoogleMap googleMap;

    public CheckInsFragment(Context context) {
        this.context = context;
        adapter = new JournalEntryDatabaseAdapter(context);

        Cursor cursor = adapter.getAll();

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

                cursor.close();

            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View cView = inflater.inflate(R.layout.fragments_checkins, container, false);

        mMapView = (MapView) cView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        markerClicked(marker);
                    }
                });

                // For showing a move to my location button
                // googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                addMarkers();
                // For zooming automatically to the location of the marker
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        return cView;
    }

    public void addMarkers() {
        for (int i = 0; i < entries.size(); i++) {

            LatLng temp = new LatLng(entries.get(i).latitude, entries.get(i).longitude);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(temp).title(entries.get(i).title).snippet(entries.get(i).entryDate));
            marker.setTag(entries.get(i).id);
        }
    }

    public void markerClicked(Marker marker)
    {
        int id = (int) marker.getTag();

       // Toast.makeText(context, "Entry with id "+String.valueOf(id)+" was clickedd.", Toast.LENGTH_SHORT ).show();

        Intent intent = new Intent(context, EntryDetails.class);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}