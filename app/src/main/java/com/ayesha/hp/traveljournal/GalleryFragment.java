package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 29/10/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GalleryFragment extends Fragment {

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View gView = inflater.inflate(R.layout.fragment_gallery, container, false);
        return gView;
    }

}
