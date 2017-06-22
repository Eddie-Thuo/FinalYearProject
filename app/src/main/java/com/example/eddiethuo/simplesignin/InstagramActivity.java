package com.example.eddiethuo.simplesignin;

import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 10/04/2017.
 */

public class InstagramActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return new InstagramFragment().newInstance();
    }
}
