package com.example.smson.hello.other;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smson.hello.R;


public class TestSampleActivity extends ActionBarActivity {
    private static final String TAG = TestSampleActivity.class.getSimpleName();
    // 버튼 객체 초기화
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sample);

        // XML 에 id xml_Button 버튼 클릭시 "테스트" 메세지가 나타나게 처리

        mButton = (Button) findViewById(R.id.xml_Button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "테스트", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "test:테스트");
            }
        });
    }
}
