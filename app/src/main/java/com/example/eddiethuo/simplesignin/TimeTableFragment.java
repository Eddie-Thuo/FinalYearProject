package com.example.eddiethuo.simplesignin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Connectivity.FetchingDetails;
import Connectivity.RequestHandler;
import StudentsCoursesProfs.ExamDetails;

/**
 * Created by eddiethuo on 06/03/2017.
 */

public class TimeTableFragment extends Fragment {

    public static TimeTableFragment newInstance() {
        return new TimeTableFragment();
    }
    private static final String TAG = "TimeTableFragment";

    private RecyclerView mRecyclerview;
    private static final String URL = "http://192.168.0.16/RequestDetails/RequestExamDetails.php";
    private static final String URL2 = "http://137.44.90.149/RequestDetails/RequestExamDetails.php";
    private List<ExamDetails> mAllExamDetails = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchDetails().execute("839041");
        Log.i(TAG, "Background thread started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.timetable_fragment, container, false);
        mRecyclerview = (RecyclerView) v.findViewById(R.id.timetable_recycler_view);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter(mAllExamDetails);
        return v;
    }


    private class ExamTimeTableHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mModuleCodeTextView;
        private TextView mModuleTitleTextView;
        private TextView mModuleVenueTextView;
        private TextView mDateTextView;
        private TextView mTimeTextView;
        private Button mCalendarButton;

        public ExamTimeTableHolder(View itemView) {
            super(itemView);
            mModuleCodeTextView = (TextView) itemView.findViewById(R.id.module_code);
            mModuleTitleTextView = (TextView) itemView.findViewById(R.id.module_title);
            mModuleVenueTextView = (TextView) itemView.findViewById(R.id.exam_venue);
            mDateTextView = (TextView) itemView.findViewById(R.id.exam_date);
            mTimeTextView = (TextView) itemView.findViewById(R.id.exam_time);
            mCalendarButton = (Button) itemView.findViewById(R.id.calendar_button);

        }

        public void bindExamInfo(String code, String title, String venue, String date, String time) {
            mModuleCodeTextView.setText(code);
            mModuleTitleTextView.setText(title);
            mModuleVenueTextView.setText(venue);
            mDateTextView.setText(date);
            mTimeTextView.setText(time);
            mCalendarButton.setText("ADD TO CALENDAR");
            mCalendarButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, mModuleTitleTextView.getText())
                    .putExtra(CalendarContract.Events.DESCRIPTION, "Examination")
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, mModuleVenueTextView.getText())
                    .putExtra(CalendarContract.Events.EVENT_COLOR, Color.RED)
                    .putExtra(CalendarContract.Events.DTSTART, hour);
            startActivity(intent);
        }

    }

    private class TimeTableAdapter extends RecyclerView.Adapter<ExamTimeTableHolder> {


        private List<ExamDetails> mDetails;


        public TimeTableAdapter(List<ExamDetails> details) {
            mDetails = details;
        }

        @Override
        public ExamTimeTableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.timetable_list_item, parent, false);
            return new ExamTimeTableHolder(view);
        }

        @Override
        public void onBindViewHolder(ExamTimeTableHolder holder, int position) {
            ExamDetails exam = mDetails.get(position);
            String title = exam.getTitle();
            String moduleCode = exam.getModuleCode();
            String venue = exam.getVenue();
            String examDate = exam.getExamDate();
            String examTime = exam.getExamTime();
            holder.bindExamInfo(moduleCode, title, venue, examDate, examTime);

        }

        @Override
        public int getItemCount() {
            return mDetails.size();
        }

    }

    private class FetchDetails extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        public void onPreExecute() {
            dialog = ProgressDialog.show(getActivity(), "Loading Exam Details.", "Please wait...", true, true);
        }

        @Override
        public String doInBackground(String... params) {
            HashMap<String, String> param = new HashMap<>();
            param.put("studentNumber1", params[0]);
            RequestHandler requestHandler = new RequestHandler();
            String response = requestHandler.sendPostRequest(URL2, param);
            return response;
        }

        @Override
        public void onPostExecute(String response){
            FetchingDetails fetchingDetails = new FetchingDetails();
            try {
                mAllExamDetails = fetchingDetails.parseExamDetails(response);
                setUpAdapter(mAllExamDetails);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }

    }

    public void setUpAdapter(List<ExamDetails> finalDetails) {
        if (isAdded()) {
            mRecyclerview.setAdapter(new TimeTableAdapter(finalDetails));
        }
    }


}
