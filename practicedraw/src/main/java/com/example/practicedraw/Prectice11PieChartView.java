package com.example.practicedraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-25.
 */

class Prectice11PieChartView extends View {

    private Paint mPaint;

    public Prectice11PieChartView(Context context) {
        this(context, null);
    }

    public Prectice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Prectice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        canvas.save();
        canvas.translate(250,250);
        canvas.rotate(-115);
        canvas.drawLine(0,0,170,0,mPaint);
        canvas.restore();

        mPaint.setColor(Color.parseColor("#EE3B3B"));
        canvas.drawArc(new RectF(100, 100, 400, 400), -180, 130, true, mPaint);

        mPaint.setColor(Color.parseColor("#EEB422"));
        canvas.drawArc(new RectF(105, 105, 405, 405), -50, 50, true, mPaint);

        mPaint.setColor(Color.parseColor("#D15FEE"));
        canvas.drawArc(new RectF(105, 105, 405, 405), 2, 10, true, mPaint);

        mPaint.setColor(Color.parseColor("#CDC8B1"));
        canvas.drawArc(new RectF(105, 105, 405, 405), 14, 8, true, mPaint);

        mPaint.setColor(Color.parseColor("#66CDAA"));
        canvas.drawArc(new RectF(105, 105, 405, 405), 24, 60, true, mPaint);

        mPaint.setColor(Color.parseColor("#4F94CD"));
        canvas.drawArc(new RectF(105, 105, 405, 405), 86, 94, true, mPaint);
    }
}
