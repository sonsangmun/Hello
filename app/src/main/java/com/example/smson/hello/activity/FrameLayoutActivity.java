
package com.example.smson.hello.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smson.hello.R;

public class FrameLayoutActivity extends ActionBarActivity {
    Button mDownButton;
    Button mUpButton;
    ImageView mImage1;
    ImageView mImage2;
    ImageView mImage3;
    BitmapDrawable bitmapImage1;
    BitmapDrawable bitmapImage2;
    BitmapDrawable bitmapImage3;
    int counter = 0;
    int counter2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        mUpButton = (Button) findViewById(R.id.changeUpBtn);
        mDownButton = (Button) findViewById(R.id.changeDownBtn);
        mImage1 = (ImageView) findViewById(R.id.image1);
        mImage2 = (ImageView) findViewById(R.id.image2);

        bitmapImage1 = (BitmapDrawable) getResources().getDrawable(R.drawable.girl);
        bitmapImage2 = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
        bitmapImage3 = (BitmapDrawable) getResources().getDrawable(R.drawable.psp);

        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(v);
//                if (mImage1.getVisibility() == View.VISIBLE) {
//                    mImage1.setVisibility(View.INVISIBLE);
//                    mImage2.setVisibility(View.VISIBLE);
//                } else {
//                    mImage1.setVisibility(View.VISIBLE);
//                    mImage2.setVisibility(View.INVISIBLE);
//                }
            }
        });

        mDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(v);
            }
        });

    }

    private void changeImage(View imgId) {
        BitmapDrawable loadBitmapImag;
        ImageView tempImageView;
        int tempCounter = 0;

        if(imgId.getId() == R.id.changeUpBtn) {
            tempImageView = mImage1;
            counter++;
            tempCounter = counter % 3;
        } else {
            tempImageView = mImage2;
            counter2++;
            tempCounter = counter2 % 3;
        }

        if(tempCounter == 0) {
            loadBitmapImag = bitmapImage1;
        } else if(tempCounter == 1){
            loadBitmapImag = bitmapImage2;
        } else {
            loadBitmapImag = bitmapImage3;
        }

        tempImageView.setImageDrawable(loadBitmapImag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_frame_layout, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
