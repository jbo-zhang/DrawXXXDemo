package com.example.drawxxxdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-24.
 */

public class DrawXXXView extends View {

    private Paint mPaint;
    private Path mPath;

    public DrawXXXView(Context context) {
        this(context, null);
    }

    public DrawXXXView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawXXXView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //颜色
        mPaint.setColor(Color.BLACK);
        //填充模式
        mPaint.setStyle(Paint.Style.FILL);
        //STORKE和FILL_AND_STROKE模式下有用
        mPaint.setStrokeWidth(20);
        //笔触
        mPaint.setStrokeCap(Paint.Cap.BUTT);

        mPath = new Path();
        mPath.addArc(1000, 400, 1100, 500, -225, 225);
        //mPath.addArc(400, 200, 600, 400, -180, 225);
        mPath.arcTo(1100, 400, 1200, 500, -180, 225, false);
        mPath.lineTo(1100, 580);


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,100,mPaint);

        canvas.drawRect(400,100,600,300,mPaint);

        canvas.drawPoint(700,200,mPaint);

        float[] points = {0,0, 800,100, 900,100, 800,300, 900,300, 1000,500, 3000,300};
        canvas.drawPoints(points, 2/* 跳过两个数，即前两个 0 */, 8 /* 一共绘制 8 个数（4 个点）*/, mPaint);

        RectF rf = new RectF(100, 425, 300, 575);
        canvas.drawOval(rf, mPaint);

        canvas.drawLine(400,400, 600, 600, mPaint);

        canvas.drawRoundRect(700, 450, 900, 550, 20, 20, mPaint);

        canvas.drawArc(1000, 100, 1200, 300, -110, 80, true, mPaint);
        canvas.drawArc(1000, 100, 1200, 300, -20, 60, false, mPaint);
        canvas.drawArc(1000, 100, 1200, 300, 50, 30, true, mPaint);
        canvas.drawArc(1000, 100, 1200, 300, 90, 100, false, mPaint);

        canvas.drawPath(mPath, mPaint);

    }
}
