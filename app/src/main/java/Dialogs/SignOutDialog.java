package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import com.example.eddiethuo.simplesignin.R;
import com.example.eddiethuo.simplesignin.SignInFragment2;
import com.example.eddiethuo.simplesignin.SingleFragmentActivity;

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
                        //go back to SignInFragment
                        SignInFragment2 signInFragment = new SignInFragment2();
                        FragmentManager fm = getFragmentManager();
                        fm.beginTransaction().replace(R.id.fragment_container, signInFragment).commit();
                        SingleFragmentActivity.mToolbar.setTitle("Sign In Now");
                        SingleFragmentActivity.actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
                        Toast.makeText(getActivity(), "You have successfully signed out", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
}
