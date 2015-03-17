package com.example.smson.hello.event;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smson.hello.R;

public class TouchEventActivity extends ActionBarActivity {
    private TextView mTextView;
    private Button mEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        mTextView = (TextView) findViewById(R.id.textView);
        mEventBtn = (Button) findViewById((R.id.eventBtn));

        mEventBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(Color.BLUE);
                } else if(event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(Color.CYAN);
                } else if(event.getAction() == MotionEvent.ACTION_MOVE) {
                    finish();
                }

                mTextView.setText(event.toString());

                // 이벤트가 정상적으로 완료 되었으면 true, 다른 이벤트 리스너를 무시한다.
                return false;
            }
        });

        mEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
            }
        });




        mTextView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mTextView.setText(event.toString());

        return super.onTouchEvent(event);

    }
}
