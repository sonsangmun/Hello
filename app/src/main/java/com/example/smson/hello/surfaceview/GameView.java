
package com.example.smson.hello.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.smson.hello.R;

/**
 * Created by sangmun on 2015-04-09.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
// SurfaceView 를 사용하기 위해 SurfaceHolder 를 인터페이스로 선언하여 3개의 메서드를 구현해야함.
    public static final String TAG = GameView.class.getSimpleName();
    private GameViewThread mThread;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);  // 생성자에 CallBack 등록
        mThread = new GameViewThread(getHolder(), this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.psp);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bm, 10, 10, null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // SurfaceHolder.CallBack을 implements 해서 추가된 메서드에서 스레드를 시작하는 코드
        mThread.setRuning(true);
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // SurfaceHolder.CallBack을 implements 해서 추가된 메서드에서 스레드를 종료하는 코드
        boolean retry = true;
        mThread.setRuning(false);
        while(retry){
            try{
                mThread.join();
                retry = false;
            } catch (InterruptedException e){

            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
}
