package com.example.eddiethuo.simplesignin;

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
 * Created by eddiethuo on 09/04/2017.
 */

public class InstagramFragment extends Fragment {

    public static Fragment newInstance() {
        return new InstagramFragment();
    }

    private WebView mInstagramWebView;
    private ProgressBar mProgressBar;
    private final String INSTAGRAM_URL = "https://www.instagram.com/swanseauni/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.instagram_fragment, container, false);
        mProgressBar = (ProgressBar) v.findViewById(R.id.instagram_progress_bar);
        mProgressBar.setMax(100);
        mInstagramWebView = (WebView) v.findViewById(R.id.instagram_webview);
        mInstagramWebView.getSettings().setJavaScriptEnabled(true);
        mInstagramWebView.getSettings().setDisplayZoomControls(true);
        mInstagramWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if(progress == 100){
                    mProgressBar.setVisibility(View.GONE);
                }else{
                    mProgressBar.setProgress(progress);
                }
            }
        });

        mInstagramWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String title){
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                Toast.makeText(getActivity(), "Unable to load Page", Toast.LENGTH_SHORT).show();
            }
        });

        mInstagramWebView.loadUrl(INSTAGRAM_URL);
        return v;
    }

}

