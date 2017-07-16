package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import com.example.eddiethuo.simplesignin.SignInActivity;

/**
 * Created by eddiethuo on 12/03/2017.
 */

public class SignOutDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setTitle("Are you sure you wish to logout?")
                .setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent signOutIntent = new Intent(getActivity(), SignInActivity.class);
                        startActivity(signOutIntent);
                        Toast.makeText(getActivity(), "You have successfully signed out", Toast.LENGTH_LONG).show();
                        getActivity().finish();
                    }
                })
                .show();
    }

}
