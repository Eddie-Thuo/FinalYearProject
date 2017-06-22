package com.example.eddiethuo.simplesignin;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by eddiethuo on 29/09/2016.
 */

public class ProfileSummaryActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new ProfileSummaryFragment2().newInstance();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ProfileSummaryFragment2 fragment2 = new ProfileSummaryFragment2();
        fragment2.onActivityResult(requestCode, resultCode, data);
    }




}
