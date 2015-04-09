package com.example.smson.hello.surfaceview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by sangmun on 2015-04-09.
 */
public class GameViewThread extends Thread{
    private SurfaceHolder mHolder;
    private GameView mGameView;
    private boolean mRun = false;

    public GameViewThread(SurfaceHolder mHolder, GameView mGameView) {
        this.mHolder = mHolder;
        this.mGameView = mGameView;
    }

    public void setRuning(boolean run) {
        this.mRun = run;
    }

    @Override
    public void run() {
        Canvas canvas;
        while(mRun){
            canvas = null;
            try {
                canvas = mHolder.lockCanvas(null);
                synchronized(mHolder) {
                    mGameView.onDraw(canvas);
                }
            } finally {
                if(canvas != null) mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
