package com.example.eddiethuo.simplesignin;

import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 24/11/2016.
 */

public class CourseContactsActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return CourseContactsFragment.newInstance();
    }
}
