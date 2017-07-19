package com.example.eddiethuo.simplesignin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eddiethuo on 19/07/2017.
 */

public class StudentContactsFragment extends Fragment {

    public static StudentContactsFragment newInstance(){
        return new StudentContactsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        final View v = inflater.inflate(R.layout.student_contacts, container, false);
        return v;
    }
}
