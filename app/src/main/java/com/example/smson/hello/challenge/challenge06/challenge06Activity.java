package com.example.smson.hello.challenge.challenge06;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smson.hello.R;

public class challenge06Activity extends ActionBarActivity {
    private WebView mWebView;
    private EditText mEditText;
    private Button mMoveBtn;
    private Button mMoveUrlBtn;
    private LinearLayout mUrlLayout;

    /*
     Git test
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge06);

        mWebView = (WebView) findViewById(R.id.xWebView);
        mEditText = (EditText) findViewById(R.id.xInputUrl);
        mMoveUrlBtn = (Button) findViewById(R.id.xMoveUrlBtn);
        mMoveBtn = (Button) findViewById(R.id.xMoveBtn);
        mUrlLayout = (LinearLayout) findViewById(R.id.xUrlLayour);

        // actionbar
        ActionBar action = getActionBar();

        // 페이지 이동 시 새로운 창이 아닌 현재 webView 에 표시
        mWebView.setWebViewClient(new WebViewClient());

        // 주소창 버튼
        mMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUrlLayout.getVisibility() == View.VISIBLE) {
                    mUrlLayout.setVisibility(View.GONE);
                } else {
                    mUrlLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        // 주소 이동
        mMoveUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(mEditText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge06, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "메뉴1를 선택함", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.action_settings2) {
            Toast.makeText(this, "메뉴2를 선택함", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}