package Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by eddiethuo on 12/03/2017.
 */

public class SelectPhotoDialog extends DialogFragment {
    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_PHOTO_FROM_GALLERY = 2;
    private final int MAX_BITMAP_DIMENSION = 4096;
    private Button mSelectImageButton;
    private Button mTakeImageButton;

    CharSequence[] photoOptions = {"Select Existing Photo From Gallery", "Take new Photo"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Photo Option")
                .setItems(photoOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), getActivity().getClass().toString(), Toast.LENGTH_SHORT).show();
                        if (which == 0) {
                            selectImage();
                        } else {
                            final Intent CAPTURE_IMAGE = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            getActivity().startActivityForResult(CAPTURE_IMAGE, REQUEST_PHOTO);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View v = inflater.inflate(R.layout.photo_dialog, container, false);
//        mTakeImageButton = (Button) v.findViewById(R.id.take_image_button);
//        mSelectImageButton = (Button) v.findViewById(R.id.select_image_button);
//        mSelectImageButton.setOnClickListener(new PhotoListener());
//        mTakeImageButton.setOnClickListener(new PhotoListener());
//        return v;
//
//    }

    class PhotoListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == mSelectImageButton) {
                selectImage();
            } else if (v == mTakeImageButton) {
                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getActivity().startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        }
    }


    public void selectImage() {
        Intent pickImageIntent = new Intent();
        pickImageIntent.setType("image/*");
        pickImageIntent.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityForResult(pickImageIntent, REQUEST_PHOTO_FROM_GALLERY);
    }


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
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
    }*/
}



