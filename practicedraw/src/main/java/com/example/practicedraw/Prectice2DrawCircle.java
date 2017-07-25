package com.example.practicedraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-25.
 */

class Prectice2DrawCircle extends View {

    private Paint mPaint;

    public Prectice2DrawCircle(Context context) {
        this(context, null);
    }

    public Prectice2DrawCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Prectice2DrawCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,100, mPaint);
    }
}
