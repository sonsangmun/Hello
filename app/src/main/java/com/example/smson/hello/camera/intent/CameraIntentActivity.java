package com.example.smson.hello.camera.intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.smson.hello.R;

import java.io.File;
import java.io.IOException;

public class CameraIntentActivity extends ActionBarActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView mPic;
    private Uri mPicUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_intent);

        mPic = (ImageView) findViewById(R.id.iv_pic);
        findViewById(R.id.btn_camera).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 카메라 호출
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        // 사진 폴더
        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mPicUri = Uri.fromFile(new File(path, "demo.jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPicUri);

        // 주고
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    // 받고
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && mPicUri != null) {
            Bitmap selPhoto = null;
            try {
                selPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(),mPicUri);
                mPic.setImageBitmap(selPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
