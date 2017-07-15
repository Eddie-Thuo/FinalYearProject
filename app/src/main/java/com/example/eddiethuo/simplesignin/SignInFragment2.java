package com.example.eddiethuo.simplesignin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import Connectivity.RequestHandler;

/**
 * Created by eddiethuo on 26/09/2016.
 */

public class SignInFragment2 extends Fragment {


    private EditText mPasswordField;
    private EditText mStudentNoField;
    private Button mSignInButton;
    private String studentNo;
    private String password;
    private static final String URL = "http://192.168.0.7./Login/LoginRequests.php";
    private static final String URL2 = "http://192.168.0.16/Login/LoginRequests.php";
    private static final String URL3 = "http://137.44.90.149/Login/LoginRequests.php";


    public static SignInFragment2 newInstance(){
        return new SignInFragment2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.sign_in_fragment_3, container, false);
        mSignInButton = (Button) v.findViewById(R.id.sign_in_student_button);
        mPasswordField = (EditText) v.findViewById(R.id.student_password_field);
        mStudentNoField = (EditText) v.findViewById(R.id.student_number_field);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentNo = mStudentNoField.getText().toString();
                password = mPasswordField.getText().toString();
                new LoginAsyncTask().execute(studentNo,password);
            }
        });
        mStudentNoField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mStudentNoField.getText().equals("")){
                    mStudentNoField.setError("Not valid student no.");
                    studentNo = "";
                }
                else if(mStudentNoField.length() > 6 || mStudentNoField.length() < 6){
                    mStudentNoField.setError("Student Number length not valid");
                }
            }
        });
        return v;

    }

    public class LoginAsyncTask extends AsyncTask<String,Void,String>{
        ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        public void onPreExecute(){
            dialog = ProgressDialog.show(getActivity(), "Signing In...", "Please wait...", true, true);
        }
       @Override
       public String doInBackground(String... params){
           HashMap<String, String> param = new HashMap<>();
           param.put("studentNo", params[0]);
           param.put("password", params[1]);
           RequestHandler requestHandler = new RequestHandler();
           String response =  requestHandler.sendPostRequest(URL3, param);
           return response;
       }

        @Override
        public void onPostExecute(String response){
            dialog.dismiss();
            if(response.equalsIgnoreCase("Working")){
                performSignedInTransition();
                Toast.makeText(getActivity(), "Sign In Successful", Toast.LENGTH_SHORT).show();
            }else if(response.equalsIgnoreCase("Cannot Sign In")){
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
            }
            else if(response.equalsIgnoreCase("Problem")){
                String problem = "Problem Signing in. Check student no and password";
                Toast.makeText(getActivity(), problem, Toast.LENGTH_SHORT).show();
            }
            else if(response.equalsIgnoreCase("Error Registering")){
                Toast.makeText(getActivity(), "Error Registering", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), "Cannot Sign In. Try Again", Toast.LENGTH_SHORT).show();
            }
        }

        private void performSignedInTransition(){
        }
    }

}
