package com.example.eddiethuo.simplesignin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Connectivity.FetchingDetails;
import Connectivity.RequestHandler;
import Dialogs.StaffContactDialog;
import StudentsCoursesProfs.ProfDetails;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by eddiethuo on 24/11/2016.
 */

public class CourseContactsFragment extends Fragment {


    private static final String TAG = "CourseContactsFragment";
    private static final String CONTACT_STAFF = "CourseContactsFragment";
    private static final String URL = "http://192.168.0.16/RequestDetails/RequestStaffContactDetails.php";
    private static final String URL2 = "http://137.44.90.149/RequestDetails/RequestStaffContactDetails.php";
    private static final String URL3 = "https://thuoeddie.000webhostapp.com/RequestStaffContactDetails.php";
    private List<ProfDetails> mAllStaffDetails = new ArrayList<>();
    private RecyclerView mRecyclerview1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        new StaffAyncTask().execute("839041");
        Log.i(TAG, "Background thread started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.staff_contacts_fragment, container, false);
        mRecyclerview1 = (RecyclerView) v.findViewById(R.id.staff_recycler_view);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter(mAllStaffDetails);
        return v;
    }

    class StaffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Button mContactButton;
        private TextView mStaffName;
        private TextView mCodeAndModule;
        private CircleImageView mStaffImage;
        private String email;
        private String telephone;
        StaffContactDialog dialog;
        FragmentManager fm = getFragmentManager();

        public StaffViewHolder(View itemView){
            super(itemView);
            mContactButton = (Button) itemView.findViewById(R.id.contact_option_button);
            mStaffImage = (CircleImageView) itemView.findViewById(R.id.staff_image);
            mStaffName = (TextView) itemView.findViewById(R.id.staff_name);
            mCodeAndModule = (TextView) itemView.findViewById(R.id.module_title_2);
        }

        public void bindStaffInfo(String code, String title, String fullname, String staffEmail, String phoneNumber, Bitmap image){
            mCodeAndModule.setText(code + " " + title);
            mStaffName.setText(fullname);
            mStaffImage.setImageBitmap(image);
            this.email = staffEmail;
            this.telephone = phoneNumber;
            dialog = StaffContactDialog.newInstance(staffEmail, phoneNumber);
            mContactButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            dialog.show(fm, CONTACT_STAFF);
        }

    }

    class StaffAdapter extends RecyclerView.Adapter<StaffViewHolder>{

        private List<ProfDetails> mDetails;
        public StaffAdapter(List<ProfDetails> allDetails){
            mDetails = allDetails;
        }

        @Override
        public StaffViewHolder onCreateViewHolder(ViewGroup container, int viewType){
            LayoutInflater inflater =  LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.course_contacts_list_item, container, false);
            return new StaffViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StaffViewHolder holder, int position){
            ProfDetails details = mDetails.get(position);
            String fullname = details.getFullname();
            String code = details.getModuleCode();
            String title = details.getTitle();
            String email = details.getEmail();
            String telephone = details.getTelephone();
            Bitmap image = decodeImageString(details.getEncodedImage());
            holder.bindStaffInfo(code, title, fullname, email, telephone, image);
        }

        @Override
        public int getItemCount(){
            return mDetails.size();
        }

        public Bitmap decodeImageString(String encodedImage){
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(decodedString,0, decodedString.length);
            return image;
        }
    }

    private class StaffAyncTask extends AsyncTask<String, Void, String>{

        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        public void onPreExecute() {
            dialog = ProgressDialog.show(getActivity(), "Loading Staff Details.", "Please wait...", true, true);
        }

        @Override
        public String doInBackground(String... params) {
            HashMap<String, String> param = new HashMap<>();
            param.put("studentNumber2", params[0]);
            RequestHandler requestHandler = new RequestHandler();
            String response = requestHandler.sendPostRequest(URL3, param);
            return response;
        }

        @Override
        public void onPostExecute(String response){
            FetchingDetails fetchingDetails = new FetchingDetails();
            try {
                mAllStaffDetails = fetchingDetails.parseStaffContactDetails(response);
                setUpAdapter(mAllStaffDetails);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }

    }

    public void setUpAdapter(List<ProfDetails> finalDetails){
        if(isAdded()){
            mRecyclerview1.setAdapter(new StaffAdapter(finalDetails));
        }
    }


}
