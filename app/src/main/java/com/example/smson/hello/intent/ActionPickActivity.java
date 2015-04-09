package com.example.smson.hello.intent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smson.hello.R;

public class ActionPickActivity extends Activity {
    public static final String TAG = ActionPickActivity.class.getSimpleName();
    private final int REQ_ACTION_PICK = 100;
    private final int REQ_ACTION_PICK_CROP = 200;
    private ImageView mImageView;
    private Button mClickButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_pick);

        mImageView = (ImageView)findViewById(R.id.img_area);
        findViewById(R.id.action_pick).setOnClickListener(L1);
        findViewById(R.id.action_pick_crop).setOnClickListener(L1);
    }

    // 갤러리에서 이미지를 선택하면 실행된다.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, requestCode + ":" + resultCode + ":" + data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQ_ACTION_PICK){
                Uri uri = data.getData();
                mImageView.setImageURI(uri);
            }
            else if(requestCode == REQ_ACTION_PICK_CROP){
                Bitmap bitmap = data.getParcelableExtra("data");
                mImageView.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    };

    private void actionPick(){
        Intent pickerIntent = new Intent(Intent.ACTION_PICK);
        pickerIntent.setType("image/*");
        startActivityForResult(pickerIntent, REQ_ACTION_PICK);
    }


    private void actionPickCrop(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 280);
        intent.putExtra("outputY", 280);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQ_ACTION_PICK_CROP);
    }

    public View.OnClickListener L1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.action_pick:
                    actionPick();
                    break;
                case R.id.action_pick_crop:
                    actionPickCrop();
                    break;
                default:
                    break;
            }
        }
    };
}
