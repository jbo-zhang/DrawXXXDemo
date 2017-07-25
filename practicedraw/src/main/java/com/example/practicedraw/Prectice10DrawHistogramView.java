package com.example.practicedraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-25.
 */

class Prectice10DrawHistogramView extends View {

    private Paint mPaint;
    private TextPaint mTextPaint;

    public Prectice10DrawHistogramView(Context context) {
        this(context, null);
    }

    public Prectice10DrawHistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Prectice10DrawHistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLACK);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(12);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //刻度
        for (int i = 0; i < 50; i++) {
            if(i % 5 == 0) {
                canvas.drawLine(100,100 + i * 5, 105, 100 + i * 5, mPaint);
                canvas.drawText("" + (50-i), 80, 100 + i * 5 + 3, mTextPaint);
            }
        }
        //X轴
        canvas.drawLine(100, 80, 100, 350, mPaint);
        //x轴箭头
        canvas.drawLine(100,80, 95, 90,mPaint);
        canvas.drawLine(100,80, 105,90,mPaint);

        //y轴
        canvas.drawLine(100, 350, 500, 350, mPaint);
        //y轴箭头
        canvas.drawLine(500, 350, 490, 345 ,mPaint);
        canvas.drawLine(500, 350, 490, 355 ,mPaint);

        drawHistogram(canvas, Color.YELLOW, new RectF(110, 350, 150,200), mPaint);

        drawHistogram(canvas, Color.RED, new RectF(150, 350, 190,150), mPaint);

        drawHistogram(canvas, Color.BLUE, new RectF(190, 350, 230,300), mPaint);

        drawHistogram(canvas, Color.GREEN, new RectF(230, 350, 270, 180), mPaint);

        drawHistogram(canvas, Color.CYAN, new RectF(270,350,310,100),mPaint);

        drawHistogram(canvas, Color.LTGRAY, new RectF(310,350,350,190),mPaint);

        drawHistogram(canvas, Color.MAGENTA, new RectF(350,350,390,250),mPaint);



    }

    private void drawHistogram(Canvas canvas, int color, RectF rect, Paint mPaint) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawRect(rect, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect, mPaint);
    }
}
