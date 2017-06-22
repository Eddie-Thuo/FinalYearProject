package com.example.eddiethuo.simplesignin;

import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 27/04/2017.
 */

public class ContactUsActvity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return new ContactInfoFragment();
    }
}
