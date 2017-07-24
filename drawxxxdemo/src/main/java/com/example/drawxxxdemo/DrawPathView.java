package com.example.drawxxxdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-24.
 */

public class DrawPathView extends View{

    private Paint mPaint;
    private Path mPath;

    public DrawPathView(Context context) {
        this(context, null);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.lineTo(100,100);
        mPath.rLineTo(100,0);
        canvas.drawPath(mPath, mPaint);

        mPath.moveTo(300,0);
        mPath.lineTo(400,100);
        mPath.moveTo(500,100);
        mPath.lineTo(500,0);
        canvas.drawPath(mPath, mPaint);

        mPath.moveTo(600,0);
        mPath.lineTo(700,100);
        //mPath.arcTo(700,100,900,300,-90,90,false);
        //addArc相当于arcTo的forceMoveTouch=true
        mPath.addArc(700,100,900,300,-90,90);

        canvas.drawPath(mPath, mPaint);

        mPath.moveTo(0, 400);
        mPath.lineTo(500,400);
        mPath.lineTo(450,450);
        //close与lineTo坐标起点是完全等价的
        mPath.close();

        canvas.drawPath(mPath, mPaint);

    }
}
