package com.example.drawtextdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

/**
 * Created by zhangjinbo on 17-7-27.
 */

public class DrawTextView extends View {

    private Paint mPaint;
    private Path mPath;
    private TextPaint mTextPaint;

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(45);

        mPath = new Path();
        mPath.moveTo(300,100);
        mPath.quadTo(450,230,300,500);

        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(50);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "Hello HenCoder!";
        //在绘制文字的时候把坐标填成 (0, 0)，
        // 文字并不会显示在 View 的左上角，而是会几乎完全显示在 View 的上方，
        // 到了 View 外部看不到的位置
        canvas.drawText(text, 0,0,mPaint);

        //drawText() 参数中的 y ，指的是文字的基线（ baseline ） 的位置
//        canvas.drawText(text, 0,100,mPaint);

        /*
        吁，拐角处的文字怎么那么难看？
        所以记住一条原则： drawTextOnPath() 使用的 Path ，
        拐弯处全用圆角，别用尖角,否则难看
        拐角用圆角不是指用CornerPathEffect，而是路径本身是圆角。
         */
//        mPaint.setPathEffect(new CornerPathEffect(50));
//        canvas.drawPath(mPath, mPaint);
//        mPaint.setPathEffect(null);
//        canvas.drawTextOnPath(text, mPath, 0, 0, mPaint);


        String longText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        //到了 View 的边缘处，文字继续向后绘制到看不见的地方，而不是自动换行
        canvas.drawText(longText, 200, 50, mPaint);


        /*
        StaticLayout 的构造方法是 StaticLayout(CharSequence source, TextPaint paint, int width, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad)，其中参数里：
        width 是文字区域的宽度，文字到达这个宽度后就会自动换行；
        align 是文字的对齐方向；
        spacingmult 是行间距的倍数，通常情况下填 1 就好；
        spacingadd 是行间距的额外增加值，通常情况下填 0 就好；
        includeadd 是指是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。
        如果你需要进行多行文字的绘制，并且对文字的排列和样式没有太复杂的花式要求，那么使用 StaticLayout 就好
         */
        canvas.save();
        canvas.translate(0,100);
        StaticLayout staticLayout1 = new StaticLayout(longText, mTextPaint, 600, Layout.Alignment.ALIGN_NORMAL,1,0,true);
        staticLayout1.draw(canvas);
        canvas.restore();

        //粗体
        mPaint.setFakeBoldText(true);
        canvas.drawText(longText, 200, 400, mPaint);

        mPaint.setFakeBoldText(false);
        //删除线
        mPaint.setStrikeThruText(true);
        canvas.drawText(longText, 200, 450, mPaint);

        mPaint.setStrikeThruText(false);
        //下划线
        mPaint.setUnderlineText(true);
        canvas.drawText(longText, 200, 500, mPaint);

        mPaint.setUnderlineText(false);

        //错切
        mPaint.setTextSkewX(-0.5f);
        canvas.drawText(longText, 200, 550, mPaint);

        mPaint.setTextSkewX(0);

        //横向缩放
        mPaint.setTextScaleX(0.8f);
        canvas.drawText(longText, 200, 600, mPaint);

        mPaint.setTextScaleX(1.0f);

        //字体间距
        mPaint.setLetterSpacing(0.2f);
        canvas.drawText(longText, 200, 650, mPaint);

        mPaint.setLetterSpacing(0);

        //用 CSS 的 font-feature-settings 的方式来设置文字。
        mPaint.setFontFeatureSettings("smcp");   // 设置 "small caps"
        canvas.drawText(longText, 200, 700, mPaint);

        mPaint.setFontFeatureSettings(null);

        //设置文字的对齐方式。一共有三个值：LEFT CETNER 和 RIGHT。默认值为 LEFT。
        //即起点（200,750）变为文字的起点， 中心，终点
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(text, 200, 750, mPaint);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, 200, 800, mPaint);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(text, 200, 850, mPaint);

        mPaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawColor(Color.WHITE);
        canvas.translate(0,-500);

        String localeText = "雨骨底条今直沿微写";
        //地域,坚果真机Android5.1.2无效果，模拟器Android8.0有效果
        mPaint.setTextLocale(Locale.CHINA); //简体中文
        canvas.drawText(localeText, 200, 900, mPaint);
        mPaint.setTextLocale(Locale.TAIWAN);  //繁体中文
        canvas.drawText(localeText, 200, 950, mPaint);
        mPaint.setTextLocale(Locale.JAPAN);
        canvas.drawText(localeText, 200, 1000, mPaint);
        mPaint.setTextLocale(Locale.ENGLISH);
        canvas.drawText(localeText, 200, 1050, mPaint);


        //设置是否启用字体的 hinting （字体微调），微调的会比较清晰，但是手机像素密度高了就没啥体现了
        mPaint.setTextSize(16);
        mPaint.setHinting(Paint.HINTING_ON);
        canvas.drawText("雨骨底条今直沿微写", 200, 1050, mPaint);
        mPaint.setHinting(Paint.HINTING_OFF);
        canvas.drawText("雨骨底条今直沿微写", 200, 1100, mPaint);

        //是否开启次像素级的抗锯齿（ sub-pixel anti-aliasing ）。
        //和前面讲的字体 hinting 一样，
        // 由于现在手机屏幕像素密度已经很高，
        // 所以默认抗锯齿效果就已经足够好了，
        // 一般没必要开启次像素级抗锯齿，
        // 所以这个方法基本上没有必要使用
        mPaint.setSubpixelText(true);

    }
}
