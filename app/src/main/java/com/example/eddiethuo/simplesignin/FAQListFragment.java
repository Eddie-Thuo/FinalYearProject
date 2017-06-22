package com.example.eddiethuo.simplesignin;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by eddiethuo on 04/01/2017.
 */

public class FAQListFragment extends Fragment {
    public static final String EXTRA_QUESTION = "question";
    public static final String EXTRA_ANSWER = "answer";
    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;

    public static Fragment newInstance(){
        return new FAQListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.faq_fragment, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.faq_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new QuestionAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    public class QuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mQuestionView;
        public CardView mCardView;
        public String answer;
        public String question;
        public QuestionHolder(View itemView){
            super(itemView);
            mQuestionView = (TextView) itemView.findViewById(R.id.question_text_view);
            mCardView = (CardView) itemView.findViewById(R.id.question_card_view);
            itemView.setOnClickListener(this);
        }

        private void bindQuestionsAndAnswers(String question, String answer){
            mQuestionView.setText(question);
            this.answer = answer;
            this.question = question;
        }
        @Override
        public void onClick(View v){
            //Intent intent = FAQListActivity.newIntent(getActivity(), question, answer);
            Intent intent2 = AnswerActivity.newIntent(getActivity(), question, answer);
            startActivity(intent2);
        }
    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>{

        Resources res = getResources();
        private String[] allQuestions = res.getStringArray(R.array.faq_questions);
        private String[] allAnswers = res.getStringArray(R.array.faq_answers);

        @Override
        public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater inflater  = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.faq_list_item, parent, false);
            return new QuestionHolder(view);
        }

        @Override
        public void onBindViewHolder(QuestionHolder holder, int position){
            String question = allQuestions[position];
            String answer = allAnswers[position];
            holder.bindQuestionsAndAnswers(question, answer);
        }

        @Override
        public int getItemCount(){
            return allQuestions.length;
        }
    }

   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.question_search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        mSearchView.setIconified(true);
        EditText searchEditText = (EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(Color.BLACK);
    }*/

    @Override
    public void onResume(){
        super.onResume();
        //Log.i("Toolbar in FAQListFragment", SingleFragmentActivity.mToolbar.getTitle().toString());
    }

}
