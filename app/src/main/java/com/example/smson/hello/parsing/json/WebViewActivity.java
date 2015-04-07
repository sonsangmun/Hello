package com.example.smson.hello.parsing.json;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import com.example.smson.hello.R;

public class WebViewActivity extends ActionBarActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.webview_recipe);

        // url 값 받아오기
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("url");
            mWebView.loadUrl(url);
        }
    }
}
