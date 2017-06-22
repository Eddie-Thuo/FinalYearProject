package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by eddiethuo on 12/04/2017.
 */

public class StaffContactDialog extends DialogFragment {
    private static final String EMAIL_TAG = "email";
    private static final String NUMBER_TAG = "number";
    private String staffEmail;
    private String number;
    final String[] choices = {"Compose Email", "Call Member Of staff"};

    public static StaffContactDialog newInstance(String email, String phoneNumber){
        StaffContactDialog dialog = new StaffContactDialog();
        Bundle args  = new Bundle();
        args.putString(EMAIL_TAG, email);
        args.putString(NUMBER_TAG, phoneNumber);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.staffEmail = getArguments().getString(EMAIL_TAG);
        this.number = getArguments().getString(NUMBER_TAG);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose Messaging Option")
                .setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            // create email intent
                            createEmailIntent();
                        }
                        else{
                            // create phone intent
                            createPhoneIntent();

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                })
                .create();
    }

    public void createEmailIntent(){
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setType("text/plain");
        sendEmail.putExtra(Intent.EXTRA_TEXT,"");
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "");
        sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{staffEmail});
        startActivity(sendEmail);
    }

    public void createPhoneIntent(){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        Uri numberUri = Uri.parse("tel:" + number);
        callIntent.setData(numberUri);
        startActivity(callIntent);
    }
}
