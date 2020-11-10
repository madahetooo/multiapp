package com.yat.helloworld.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

public class JavaTpointWebActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_java_tpoint_web, container, false);


        WebView javaTpointWebSite = v.findViewById(R.id.web_javaTpoint);


        javaTpointWebSite.getSettings().setJavaScriptEnabled(true);  // show the designs
        javaTpointWebSite.setWebViewClient(new WebViewClient());
        javaTpointWebSite.loadUrl("https://www.javatpoint.com/");
        return v;

    }

}