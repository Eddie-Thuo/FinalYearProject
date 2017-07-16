package com.example.eddiethuo.simplesignin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by eddiethuo on 08/01/2017.
 */

public class AnswerActivity extends AppCompatActivity {

    public static final String EXTRA_QUESTION = "question";
    public static final String EXTRA_ANSWER = "answer";

    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private LinearLayout mLinearLayout;
    private String question;
    private String answer;
    private Toolbar mToolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_fragment);
        question = getIntent().getStringExtra(EXTRA_QUESTION);
        answer = getIntent().getStringExtra(EXTRA_ANSWER);
        mQuestionTextView = (TextView) findViewById(R.id.question);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        mQuestionTextView.setText(question);
        mAnswerTextView.setText(answer);
        mToolbar = (Toolbar) findViewById(R.id.question_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static Intent newIntent(Context packageContext, String question, String answer) {
        Intent intent = new Intent(packageContext, AnswerActivity.class);
        intent.putExtra(EXTRA_QUESTION, question);
        intent.putExtra(EXTRA_ANSWER, answer);
        return intent;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}
