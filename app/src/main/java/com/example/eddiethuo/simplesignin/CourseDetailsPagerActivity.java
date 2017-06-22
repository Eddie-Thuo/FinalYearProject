package com.example.eddiethuo.simplesignin;

import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 25/11/2016.
 */

public class CourseDetailsPagerActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return CourseDetailsPagerFragment.newInstance();
    }
}
