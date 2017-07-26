package com.example.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-25.
 * Paint 最后一层处理颜色的方法是 setXfermode(Xfermode xfermode) ，它处理的是「当颜色遇上 View」的问题。
 * "Xfermode" 其实就是 "Transfer mode"，用 "X" 来代替 "Trans" 是一些美国人喜欢用的简写方式
 */

public class XfermodeView extends View{

    private Xfermode mXfermode;
    private Paint mPaint;
    private Bitmap rectBitmap;
    private Bitmap circleBitmap;

    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        
    }

    private void init() {
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint = new Paint();
        rectBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_contact_call_normal);
        circleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_next_red);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        要想使用 setXfermode() 正常绘制，
        必须使用离屏缓存 (Off-screen Buffer) 把内容绘制在额外的层上，
        再把绘制好的内容贴回 View 中
        使用离屏缓冲有两种方式：
            Canvas.saveLayer()
        和
            View.setLayerType()

        明确一点：先绘制就是下层是DST， 后绘制即上层是SRC


         */
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(rectBitmap, 0, 0, mPaint);    //画方
        mPaint.setXfermode(mXfermode);  //设置Xfermode
        canvas.drawBitmap(circleBitmap,0,0,mPaint);     //画圆
        mPaint.setXfermode(null);// 用完及时清除 Xfermode

        canvas.restoreToCount(saved);
    }
}
