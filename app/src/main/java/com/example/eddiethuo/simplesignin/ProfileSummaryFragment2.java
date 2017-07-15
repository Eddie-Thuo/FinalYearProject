package com.example.eddiethuo.simplesignin;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import Dialogs.SelectPhotoDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by eddiethuo on 29/09/2016.
 */

public class ProfileSummaryFragment2 extends Fragment {

    private FloatingActionButton mPicButton;
    public static CircleImageView mImageView;
    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_PHOTO_FROM_GALLERY = 2;
    private static final String PHOTO_DIALOG = "photo dialog";
    private final int MAX_BITMAP_DIMENSION = 4096;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_summary_fragment_4, container, false);
        mPicButton = (FloatingActionButton) v.findViewById(R.id.pic_button);
        mImageView = (CircleImageView) v.findViewById(R.id.profile_image_space);
        mPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoDialog dialog = new SelectPhotoDialog();
                FragmentManager fm = getFragmentManager();
                dialog.show(fm, PHOTO_DIALOG);

            }
        });
        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ProfileSummaryFragment2.mImageView.setImageBitmap(photo);

        } else if (requestCode == REQUEST_PHOTO_FROM_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            Uri photoData = data.getData();
            ContentResolver cr = getActivity().getContentResolver();

            try {
                Bitmap photoChosen = MediaStore.Images.Media.getBitmap(cr, photoData);
                int height = photoChosen.getHeight();
                int width = photoChosen.getWidth();
                if (height > MAX_BITMAP_DIMENSION || width > MAX_BITMAP_DIMENSION) {
                    Toast.makeText(getActivity(), "Image chosen too big to display!", Toast.LENGTH_SHORT).show();

                } else {
                    ProfileSummaryFragment2.mImageView.setImageBitmap(photoChosen);
                    Toast.makeText(getActivity(), "Image Added", Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                Log.d("Bitmap Error", "SelectPhotoDialog");
            }

        }
    }


}
