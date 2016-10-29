package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 29/10/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsTourFragment extends Fragment {

    public NewsTourFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View nView = inflater.inflate(R.layout.fragment_newstour, container, false);
        return nView;
    }

}


