package com.example.eddiethuo.simplesignin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by eddiethuo on 07/01/2017.
 */

public class AnswerFragment extends Fragment {
    public static final String EXTRA_QUESTION = "question";
    public static final String EXTRA_ANSWER = "answer";

    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private LinearLayout mLinearLayout;
    private String question;
    private String answer;

    public static AnswerFragment newInstance(){
        AnswerFragment fragment = new AnswerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v  = inflater.inflate(R.layout.answer_fragment, container, false);
        mQuestionTextView = (TextView)v.findViewById(R.id.question);
        mLinearLayout = (LinearLayout)v.findViewById(R.id.linear_layout);
        mAnswerTextView = (TextView)v.findViewById(R.id.answer_text_view);
        mQuestionTextView.setText(question);
        mAnswerTextView.setText(answer);
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        question = getActivity().getIntent().getStringExtra(EXTRA_QUESTION);
        answer = getActivity().getIntent().getStringExtra(EXTRA_ANSWER);
    }
}