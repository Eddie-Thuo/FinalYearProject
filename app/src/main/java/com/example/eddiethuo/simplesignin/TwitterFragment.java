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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by eddiethuo on 27/11/2016.
 */

public class TwitterFragment extends Fragment {

    public static Fragment newInstance() {
        return new TwitterFragment();
    }

    private WebView mWebView;
    private ProgressBar mLoadingWebPage;
    private static final String TWITTER_URL = "https://twitter.com/swanseacompsci";
    ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.twitter_fragment, container, false);
        mLoadingWebPage = (ProgressBar) v.findViewById(R.id.progress_bar);
        mLoadingWebPage.setMax(100);
        mWebView = (WebView) v.findViewById(R.id.twitter_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    mLoadingWebPage.setVisibility(View.GONE);
                } else {
                    mLoadingWebPage.setVisibility(View.VISIBLE);
                    mLoadingWebPage.setProgress(progress);
                }
            }


        });


        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getActivity(), "Unable to load Page", Toast.LENGTH_SHORT).show();
            }


        });

        mWebView.loadUrl(TWITTER_URL);
        return v;
    }


}
