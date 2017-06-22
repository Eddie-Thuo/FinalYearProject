package com.example.eddiethuo.simplesignin;

import android.content.Context;

/**
 * Created by eddiethuo on 07/03/2017.
 */

public class ExamTimetable {

    private static ExamTimetable examTimetable = null;
    private String[] codes = {"CSC-M37", "CSC-M38", "CSC-M39"};
    private String[] titles = {"Programming 1", "Programming 2", "Programming 3"};
    private String[] venues = {"Brangwyn Hall, Victoria Park", "Brangwyn Hall, Victoria Park", "Brangwyn Hall, Victoria Park"};
    private ExamTimetable(){
    }

    public static ExamTimetable getSingleton(Context context){
        if(examTimetable == null){
            examTimetable = new ExamTimetable();
        }
        return examTimetable;
    }

    public String[] getCodes(){
        return codes;
    }

    public String[] getTitles(){
        return titles;
    }

    public String[] getVenues(){
        return venues;
    }

}
