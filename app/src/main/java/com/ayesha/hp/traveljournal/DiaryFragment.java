package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 29/10/16.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DiaryFragment extends Fragment {

    EntriesListAdapter adapter;
    Context context;

    public DiaryFragment(Context context) {
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View dView = inflater.inflate(R.layout.fragment_diary, container, false);
        ListView list = (ListView) dView.findViewById(R.id.entriesList);

        adapter = new EntriesListAdapter(context);
        list.setAdapter(adapter);
        return dView;
    }

}