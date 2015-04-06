package com.example.smson.hello.bitmap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * 직접 만든 뷰 위에 이미지를 그리는 방법에 대해 알 수 있습니다.
 */
public class BitmapMainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bitmap_main);

        // 직접 만든 뷰를 화면에 설정
        CustomViewImage cvi = new CustomViewImage(this);
        setContentView(cvi);
    }
}