package com.example.eddiethuo.simplesignin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by eddiethuo on 27/11/2016.
 */

public class StudentsUnionFragment extends Fragment {

    public static Fragment newInstance(){
        return new StudentsUnionFragment();
    }

    private WebView mWebView;
    private ProgressBar mProgressbar;
    private static final String UNION_URL = "https://www.swansea-union.co.uk";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.student_union_fragment, container, false);
        mWebView = (WebView)v.findViewById(R.id.students_union_web_view);
        mProgressbar = (ProgressBar) v.findViewById(R.id.student_union_progress_bar);
        mProgressbar.setMax(100);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int progress){
                if(progress == 100){
                    mProgressbar.setVisibility(View.GONE);
                }
                else{
                    mProgressbar.setVisibility(View.VISIBLE);
                    mProgressbar.setProgress(progress);
                }
            }

        });
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String title){
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                Toast.makeText(getActivity(), "Unable to load Page", Toast.LENGTH_SHORT).show();
            }

        });

        mWebView.loadUrl(UNION_URL);
        return v;
    }
}
