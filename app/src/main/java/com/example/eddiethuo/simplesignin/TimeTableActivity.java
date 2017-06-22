package com.example.eddiethuo.simplesignin;

import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 07/03/2017.
 */

public class TimeTableActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return TimeTableFragment.newInstance();
    }
}
