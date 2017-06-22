package com.example.eddiethuo.simplesignin;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eddiethuo on 26/09/2016.
 */

public class SignInActivity extends SingleFragmentActivity{

    @Override
    public Fragment createFragment(){
        return new SignInFragment2().newInstance();
    }
}
