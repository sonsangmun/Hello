package com.example.smson.hello.surfaceview;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    public static final String TAG = GameActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
