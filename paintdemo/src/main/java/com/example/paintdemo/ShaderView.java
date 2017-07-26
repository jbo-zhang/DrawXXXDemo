package com.example.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-25.
 */

public class ShaderView extends View {

    private Paint mPaint;

    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        //在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用
        mPaint.setColor(Color.YELLOW);
        mPaint.setAntiAlias(true);
        /*
            线性渐变
            x0 y0 x1 y1：渐变的两个端点的位置
            color0 color1 是端点的颜色
            tile：端点范围之外的着色规则，类型是 TileMode

            若设置的渐变范围比画的图形的范围大，则TileMode无意思
            当设置的渐变范围比画的图形的范围小，则扩展部分由TileMode决定
         */

        Shader linearGradient = new LinearGradient(100,100,500,500,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);

        /*
            centerX centerY：辐射中心的坐标
            radius：辐射半径
            centerColor：辐射中心的颜色
            edgeColor：辐射边缘的颜色
            tileMode：辐射范围之外的着色模式。
         */
        Shader radialGradiant = new RadialGradient(300,300,50,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

        /*
            cx cy ：扫描的中心
            color0：扫描的起始颜色
            color1：扫描的终止颜色
         */
        Shader sweepGradient = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));

        /*
            bitmap：用来做模板的 Bitmap 对象
            tileX：横向的 TileMode
            tileY：纵向的 TileMode。
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pretty_girl);
        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);


        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_person_s);
        Shader bitmapShader1 = new BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        //ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果。

        //SRC_OVER 叠加且原图在上 DST_OVER 叠加且目标图在上

        //SRC_IN 只取交集的地方且交集的地方取原图， DST_IN 只取交集的地方且交集的地方取目标图
        //SRC_OUT 取原图去掉交集的地方， DST_OUT 取目标图去掉交集的地方， XOR 两者都取，但是去掉交集的地方

        //SRC_ATOP 取交集的地方与目标图，交集的地方取原图，DST_ATOP 取交集的地方与原图，交集的地方取目标图

        //CLEAR 都不取，透明


        Shader composeShader = new ComposeShader(bitmapShader, bitmapShader1, PorterDuff.Mode.XOR);

        mPaint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(500,400,400,mPaint);

        canvas.drawRect(new RectF(0,0,500,500),mPaint);


    }
}
