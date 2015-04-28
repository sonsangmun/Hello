package com.example.smson.hello.camera.builtin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smson.hello.R;

import java.io.IOException;

// <uses-permission android:name="android.permission.CAMERA" /> 매니페스트 추가 해야 됨
// 1. 카메라 프리뷰 보기 위해 SurfaceView 를 써야만 됨. 고속 렌더링이 가능 View
// 2. SurfaceView 의 holder 에 Callback 을 연결. SurfaceHolder.Callback 구현
// 3. Camera 객체를 각가의 콜백에서 사용
public class CameraBuiltinActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener, Camera.FaceDetectionListener {

    private static final String TAG = CameraBuiltinActivity.class.getSimpleName();
    private SurfaceView mPreview;
    private Button mBtnShutter;
    private Button mBtnFlash;
    private Camera mCamera;
    private CameraOverlayView mCameraOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_builtin);

        mPreview = (SurfaceView) findViewById(R.id.surfaceView);
        mBtnShutter = (Button) findViewById(R.id.btn_shutter);
        mBtnFlash = (Button) findViewById(R.id.btn_flash);

        mCameraOverlayView = (CameraOverlayView) findViewById(R.id.overlayView);

        mPreview.getHolder().addCallback(this);
        mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mBtnShutter.setOnClickListener(this);
        mBtnFlash.setOnClickListener(this);
    }

    /**
     * 카메라 후래쉬가 꺼져 있으면 켜고
     * 켜 있으면 끈다.
     */
    public void flashOnOff(){
        Camera.Parameters mCameraParameter;
        mCameraParameter = mCamera.getParameters();

        if("torch".equals(mCameraParameter.getFlashMode())){
            mBtnFlash.setText("후래쉬 ON");
            // 카메라 후레쉬 끄기
            mCameraParameter.setFlashMode("off");
            mCamera.setParameters(mCameraParameter);
            // 위 방법 또는 아래와 같이 할수도 있다.
//            mCamera.release();
        } else {
            mBtnFlash.setText("후래쉬 OFF");
            // 카메라 후레쉬 켜기
            mCameraParameter.setFlashMode("torch");
            mCamera.setParameters(mCameraParameter);
        }

//        Toast.makeText(getApplicationContext(), "value " + mCameraParameter.getFlashMode(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        mCamera.setFaceDetectionListener(this);

        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        mCamera.startPreview();

        // 얼굴 인식 기능 체크
        Camera.Parameters params = mCamera.getParameters();
        if (params.getMaxNumDetectedFaces() > 0) {
            // 얼굴 인식 시작
            mCamera.startFaceDetection();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 카메라 메모리 해제
        mCamera.stopFaceDetection();
        mCamera.release();
        mCamera = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_shutter:
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        // 1차원 배열 data를 2차원 data로 변환해서 Bitmap 으로 리턴
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, null);

                        // 사진폴더에 저장. 파일 이름은 자동으로 부여 됨
                        String savedImageUri = MediaStore.Images.Media.insertImage(getContentResolver(), bmp, "", null);

                        // 저장된 파일의 uri 를 취함
                        Uri uri = Uri.parse(savedImageUri);

                        // Media Scan
                        // 사진 앱 들의 db를 갱신하기 위해서 (사진 앱을 실행했을 때 이 파일이 바로 검색 되도록)
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

                        Toast.makeText(CameraBuiltinActivity.this, "사진이 저장되었습니다", Toast.LENGTH_SHORT).show();

                        // 다시 프리뷰를 작동
                        camera.startPreview();
                    }
                });
                break;
            case R.id.btn_flash:
                flashOnOff();
                break;
        }

    }

    @Override
    protected void onPause() {
        if(mCamera != null){
            mCamera.release();
        }
        super.onPause();
    }

    /**
     * 카메라가 있는지 없는지 확인.
     * @param context
     * @return
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // 카메라가 최소한 한개 있는 경우 처리
            Log.d(TAG, "Number of available camera : " + Camera.getNumberOfCameras());
            return true;
        } else {
            // 카메라가 전혀 없는 경우
            Toast.makeText(context, "No camera found!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // 사용가능 여부를 확인
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            // open() 의 매개변수로 int 값을 받을 수 도 있는데, 일반적으로 0이 후면 카메라, 1이 전면 카메라를 의미
            // 보다 명확히 하자면, 카메라 id 값의 범위는 0 부터 Camera.getNumberOfCameras()-1 까지 인데,
            // 대체로 Camera.getNumberOfCameras() 가 2 입니다. (폰의 경우 대게 전후 각각 한개씩 카메라가 있으므로...)
            c = Camera.open();
        } catch (Exception e){
            // 사용중이거나 사용 불가능한 경우
        }
        return c;
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        mCameraOverlayView.setFaces(faces);
    }


    // 참고
    //
    // 1. SurfaceView 상속
    // 2. SurfaceHolder.Callback 구현
    // 3. 생성자에서 getHolder().addCallback(this)
    public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private Camera mCamera;

        public CameraSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mCamera = Camera.open();

            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mCamera.startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // 카메라 메모리 해제
            mCamera.release();
            mCamera = null;
        }
    }
}
