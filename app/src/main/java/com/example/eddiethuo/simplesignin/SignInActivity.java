package com.example.eddiethuo.simplesignin;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.HashMap;
import Connectivity.RequestHandler;

/**
 * Created by eddiethuo on 26/09/2016.
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private final String URL = "https://thuoeddie.000webhostapp.com/LoginRequests.php";
    private Button mSignInStudentButton;
    private ImageView mSwanseaImageView;
    private EditText mStudentNoField;
    private EditText mStudentPasswordField;
    private String password;
    private String studentNo;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_fragment_3);
        mSignInStudentButton = (Button) findViewById(R.id.sign_in_student_button);
        mSwanseaImageView = (ImageView) findViewById(R.id.swansea_image_view);
        mStudentNoField = (EditText) findViewById(R.id.student_number_field);
        mStudentPasswordField = (EditText) findViewById(R.id.student_password_field);
        mStudentNoField.setOnClickListener(this);
        mStudentPasswordField.setOnClickListener(this);
        mSignInStudentButton.setOnClickListener(this);
        setUpAnimations();

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.student_number_field:
                if(mStudentNoField.getText().equals("")){
                    mStudentNoField.setError("Student No too short.");
                }
                break;
            case R.id.student_password_field:
                if(mStudentPasswordField.getText().equals("")) {
                    mStudentPasswordField.setError("Must be filled");
                }
                break;
            case R.id.sign_in_student_button:
                studentNo = mStudentNoField.getText().toString();
                password = mStudentPasswordField.getText().toString();
                new LoginTask().execute(studentNo, password);
                break;
        }
    }


    private class LoginTask extends AsyncTask<String, Void, String>{
        ProgressDialog dialog = new ProgressDialog(SignInActivity.this);
        @Override
        public void onPreExecute(){
            dialog = ProgressDialog.show(SignInActivity.this, "Signing In...", "Please Wait");
        }

        @Override
        public String doInBackground(String... params) {
            HashMap<String, String> param = new HashMap<>();
            param.put("studentNo", params[0]);
            param.put("password", params[1]);
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendPostRequest(URL, param);
        }

        @Override
        public void onPostExecute(String response){
            dialog.dismiss();
            if(response.equalsIgnoreCase("Working")){
                performSignedInTransition();
                Toast.makeText(getApplicationContext(), "Sign In Successful", Toast.LENGTH_SHORT).show();
            }else if(response.equalsIgnoreCase("Cannot Sign In")){
                Toast.makeText(SignInActivity.this, response, Toast.LENGTH_SHORT).show();
            }
            else if(response.equalsIgnoreCase("Problem")){
                String problem = "Problem Signing in. Check student no and password";
                Toast.makeText(SignInActivity.this, problem, Toast.LENGTH_SHORT).show();
            }
            else if(response.equalsIgnoreCase("Error Registering")){
                Toast.makeText(SignInActivity.this, "Error Registering", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(SignInActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void performSignedInTransition(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SignInActivity.this).toBundle());
        finishAfterTransition();
    }
    private void setUpAnimations(){
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setExitTransition(fade);
        getWindow().setReenterTransition(fade);
        getWindow().setAllowReturnTransitionOverlap(false);
    }
}
