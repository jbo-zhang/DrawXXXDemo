package com.example.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-25.
 */

public class ColorFilterView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public ColorFilterView(Context context) {
        this(context, null);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

        /*
          参数里的 mul 和 add 都是和颜色值格式相同的 int 值，其中 mul 用来和目标像素相乘，add 用来和目标像素相加：
           R' = R * mul.R / 0xff + add.R
           G' = G * mul.G / 0xff + add.G
           B' = B * mul.B / 0xff + add.B

           一个「保持原样」的「基本 LightingColorFilter 」，mul 为 0xffffff，add 为 0x000000（也就是0），那么对于一个像素，它的计算过程就是：
           R' = R * 0xff / 0xff + 0x0 = R // R' = R
           G' = G * 0xff / 0xff + 0x0 = G // G' = G
           B' = B * 0xff / 0xff + 0x0 = B // B' = B
         */
        /*
            去掉红色
            如果你想去掉原像素中的红色，可以把它的 mul 改为 0x00ffff （红色部分为 0 ） ，那么它的计算过程就是：
            R' = R * 0x0 / 0xff + 0x0 = 0 // 红色被移除
            G' = G * 0xff / 0xff + 0x0 = G
            B' = B * 0xff / 0xff + 0x0 = B
         */
        ColorFilter removeRedFilter = new LightingColorFilter(0x00ffff,0x000000);

        /*
            绿色更亮
            如果你想让它的绿色更亮一些，就可以把它的 add 改为 0x003000 （绿色部分为 0x30 ），那么它的计算过程就是：
            R' = R * 0xff / 0xff + 0x0 = R
            G' = G * 0xff / 0xff + 0x30 = G + 0x30 // 绿色被加强
            B' = B * 0xff / 0xff + 0x0 = B
        */
        ColorFilter addGreenFilter = new LightingColorFilter(0xffffff,0x000000);

        mPaint.setColorFilter(addGreenFilter);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pretty_girl);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.scale(0.5f,0.5f);
        canvas.drawBitmap(mBitmap,0,0, mPaint);
    }
}
