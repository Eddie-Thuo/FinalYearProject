package com.example.eddiethuo.simplesignin;

import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 27/11/2016.
 */

public class StudentUnionActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return new StudentsUnionFragment().newInstance();
    }
}
