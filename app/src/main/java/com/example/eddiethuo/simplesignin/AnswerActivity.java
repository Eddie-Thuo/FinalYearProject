package com.example.eddiethuo.simplesignin;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by eddiethuo on 08/01/2017.
 */

public class AnswerActivity extends SingleFragmentActivity {
    public static final String EXTRA_QUESTION = "question";
    public static final String EXTRA_ANSWER = "answer";

    @Override
    public Fragment createFragment(){

        return AnswerFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext, String question, String answer){
        Intent intent = new Intent(packageContext, AnswerActivity.class);
        intent.putExtra(EXTRA_ANSWER, answer);
        intent.putExtra(EXTRA_QUESTION, question);
        return intent;
    }
}
