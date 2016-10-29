package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 29/10/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CheckInsFragment extends Fragment {

    public CheckInsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View cView = inflater.inflate(R.layout.fragments_checkins, container, false);
        return cView;
    }

}