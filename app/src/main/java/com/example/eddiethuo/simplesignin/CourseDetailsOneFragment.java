package com.example.eddiethuo.simplesignin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by eddiethuo on 25/11/2016.
 */

public class CourseDetailsOneFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.course_details_one, container, false);
        return v;
    }
}
