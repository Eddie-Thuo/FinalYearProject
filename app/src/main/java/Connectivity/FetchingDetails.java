package Connectivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import StudentsCoursesProfs.ExamDetails;
import StudentsCoursesProfs.ProfDetails;

/**
 * Created by eddiethuo on 05/05/2017.
 */

public class FetchingDetails {


    public List<ExamDetails> parseExamDetails(String response)
            throws JSONException, IOException {
        List<ExamDetails> allDetails = new ArrayList<>();

        JSONArray array = new JSONArray(response);
        for (int i = 0; i < array.length(); i++) {
            JSONObject exam = array.getJSONObject(i);
            ExamDetails examDetails = new ExamDetails();
            examDetails.setModuleCode(exam.getString("moduleID"));
            examDetails.setTitle(exam.getString("mtitle"));
            examDetails.setExamDate(exam.getString("examDate"));
            examDetails.setExamTime(exam.getString("examTime"));
            examDetails.setVenue(exam.getString("venue"));
            allDetails.add(examDetails);
        }
        return allDetails;
    }

    public List<ProfDetails> parseStaffContactDetails(String response)
            throws JSONException, IOException {
        List<ProfDetails> allDetails = new ArrayList<>();

        JSONArray array = new JSONArray(response);
        for (int i = 0; i < array.length(); i++) {
            JSONObject staff = array.getJSONObject(i);
            ProfDetails profDetails = new ProfDetails();
            profDetails.setFirstName(staff.getString("firstname"));
            profDetails.setLastName(staff.getString("lastname"));
            profDetails.setFullname();
            profDetails.setModuleCode(staff.getString("moduleID"));
            profDetails.setTelephone(staff.getString("telephone"));
            profDetails.setTitle(staff.getString("mtitle"));
            profDetails.setEmail(staff.getString("email"));
            profDetails.setEncodedImage(staff.getString("image"));
            allDetails.add(profDetails);
        }
        return allDetails;
    }

}
