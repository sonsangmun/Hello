package com.example.smson.hello.camera.builtin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sonsangmun on 2015-04-24.
 */
public class CameraOverlayView extends View {
    private Paint mPaint;
    private Camera.Face[] mFaces;

    public CameraOverlayView(Context context) {
        super(context);
        initPaint();
    }

    public CameraOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CameraOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(128);
        mPaint.setTextSize(50);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mFaces == null) {
            return;
        }
        for (Camera.Face face : mFaces) {
            if (face == null) {
                continue;
            }

            Matrix matrix = new Matrix();
            matrix.postScale(getWidth() / 2000f, getHeight() / 2000f);
            matrix.postTranslate(getWidth() / 2f, getHeight() / 2f);
            int saveCount = canvas.save();
            canvas.concat(matrix);
            canvas.drawRect(face.rect, mPaint);
            canvas.restoreToCount(saveCount);
        }
    }

    public void setFaces(Camera.Face[] faces) {
        this.mFaces = faces;

        invalidate();
    }
}
