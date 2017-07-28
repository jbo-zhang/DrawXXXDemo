package com.example.drawtextdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

/**
 * Created by zhangjinbo on 17-7-28.
 */

public class TextMeasureView extends View{

    private Paint mPaint;

    public TextMeasureView(Context context) {
        this(context, null);
    }

    public TextMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text1 = "Hello HenCoder";
        String text2 = "AaJj抛物线";
        String text3 = "मैं तुझ से प्यार करता हूँ";

        canvas.drawColor(Color.GRAY);

        canvas.drawText(text1, 100,150, mPaint);
        canvas.drawLine(80,150, 800,150,mPaint);
        /*
        获取推荐的行距。
            即推荐的两行文字的 baseline 的距离。
            这个值是系统根据文字的字体和字号自动计算的。
            它的作用是当你要手动绘制多行文字（而不是使用 StaticLayout）的时候，
            可以在换行的时候给 y 坐标加上这个值来下移文字。
         */
        canvas.drawText(text2, 100, 150 + mPaint.getFontSpacing(), mPaint);
        canvas.drawLine(80,150 + mPaint.getFontSpacing(), 800,150 + mPaint.getFontSpacing(),mPaint);
        canvas.drawText(text3, 100, 150 + mPaint.getFontSpacing() *2, mPaint);
        canvas.drawLine(80,150 + mPaint.getFontSpacing() *2, 800,150 + mPaint.getFontSpacing() *2,mPaint);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        /*
        从上到下的顺序依次是top, ascent, baseline, descent, bottom,
        top与ascent是负的，descent和bottom是正的
        leading 指的是行的额外间距，即对于上下相邻的两行，上行的 bottom 线和下行的 top 线的距离
        这里在真机和虚拟机中打印出来都是0
         */

        /*
        FontMetrics 和 getFontSpacing()：

        从定义可以看出，上图中两行文字的 font spacing (即相邻两行的 baseline 的距离)
        可以通过 bottom - top + leading (top 的值为负，前面刚说过，记得吧？）来计算得出。

        但你真的运行一下会发现， bottom - top + leading 的结果是要大于 getFontSpacing() 的返回值的。

        两个方法计算得出的 font spacing 竟然不一样？

        这并不是 bug，而是因为 getFontSpacing() 的结果并不是通过 FontMetrics 的标准值计算出来的，
        而是另外计算出来的一个值，它能够做到在两行文字不显得拥挤的前提下缩短行距，以此来得到更好的显示效果。
        所以如果你要对文字手动换行绘制，多数时候应该选取 getFontSpacing() 来得到行距，不但使用更简单，显示效果也会更好。
         */

        float top = fontMetrics.top;
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float bottom = fontMetrics.bottom;
        float leading = fontMetrics.leading;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(20);
        String description = "ascent: " + ascent + " descent: " + descent
                + " top: " + top + " bottom: " + bottom + " leading: " + leading;
        canvas.drawText(description, 50, 50, paint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(80, 150 + top, 800, 150 + top, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(80, 150 + ascent, 800, 150 + ascent, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(80, 150 + descent, 800, 150 + descent, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(80, 150 + bottom, 800, 150 + bottom, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(60, 150 + bottom, 60, 150 + bottom + leading, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()+ top, 800, 150+ mPaint.getFontSpacing() + top, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()+ ascent, 800, 150 + mPaint.getFontSpacing()+ ascent, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()+ descent, 800, 150 + mPaint.getFontSpacing()+ descent, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()+ bottom, 800, 150 + mPaint.getFontSpacing()+ bottom, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()*2+ top, 800, 150+ mPaint.getFontSpacing()*2 + top, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()*2+ ascent, 800, 150 + mPaint.getFontSpacing()*2+ ascent, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()*2+ descent, 800, 150 + mPaint.getFontSpacing()*2+ descent, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(80, 150 + mPaint.getFontSpacing()*2+ bottom, 800, 150 + mPaint.getFontSpacing()*2+ bottom, mPaint);



        int offsetX = 50;
        int offsetY = 450;
        mPaint.setColor(Color.BLACK);
        String text = "Hello HenCoder!";
        canvas.drawText(text, offsetX, offsetY, mPaint);

        //获取文字的显示范围。即文字的框
        Rect bounds = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), bounds);

        bounds.left += offsetX;
        bounds.top += offsetY;
        bounds.right += offsetX;
        bounds.bottom += offsetY;

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(bounds, mPaint);

        //测量文字的宽度并返回
        /*
        如果你用代码分别使用 getTextBounds() 和 measureText() 来测量文字的宽度，
        你会发现 measureText() 测出来的宽度总是比 getTextBounds() 大一点点。
        这是因为这两个方法其实测量的是两个不一样的东西。

        一起绘制时可以看出measureText的宽度的长出框一点点

        getTextBounds: 它测量的是文字的显示范围（关键词：显示）。
        形象点来说，你这段文字外放置一个可变的矩形，然后把矩形尽可能地缩小，
        一直小到这个矩形恰好紧紧包裹住文字，那么这个矩形的范围，就是这段文字的 bounds。

        measureText(): 它测量的是文字绘制时所占用的宽度（关键词：占用）。
        前面已经讲过，一个文字在界面中，往往需要占用比他的实际显示宽度更多一点的宽度，
        以此来让文字和文字之间保留一些间距，不会显得过于拥挤。上面的这幅图，
        我并没有设置 setLetterSpacing() ，这里的 letter spacing 是默认值 0，
        但你可以看到，图中每两个字母之间都是有空隙的。
        另外，下方那条用于表示文字宽度的横线，在左边超出了第一个字母 H 一段距离的，
        在右边也超出了最后一个字母 r（虽然右边这里用肉眼不太容易分辨），
        而就是两边的这两个「超出」，导致了 measureText() 比 getTextBounds() 测量出的宽度要大一些。
         */
        float textWidth = mPaint.measureText(text);
        canvas.drawLine(offsetX, offsetY, offsetX + textWidth, offsetY, mPaint);

        /*
        获取字符串中每个字符的宽度，并把结果填入参数 widths。

        这相当于 measureText() 的一个快捷方法，
        它的计算等价于对字符串中的每个字符分别调用 measureText()
        ，并把它们的计算结果分别填入 widths 的不同元素。
         */
        float[] widths = new float[text.length()];
        mPaint.getTextWidths(text, widths);


        mPaint.setStyle(Paint.Style.FILL);
        float fontSpacing = mPaint.getFontSpacing();
        int measuredCount;
        float[] measureWidth = {0};

        /*
        breakText 指限制在一个宽度，得出这个宽度下可以绘制的文字个数和宽度

        和 measureText() 的区别是， breakText() 是在给出宽度上限的前提下测量文字的宽度。
        如果文字的宽度超出了上限，那么在临近超限的位置截断文字。
         */
        measuredCount = mPaint.breakText(text, 0, text.length(), true, 300, measureWidth);
        canvas.drawText(text, 0, measuredCount, 100, 550, mPaint);
        canvas.drawText("w:" + measureWidth[0] + " c:" + measuredCount, 0, 550, paint);


        text = "Hello HenCoder!\uD83C\uDDE8\uD83C\uDDF3";

        measuredCount = mPaint.breakText(text, 0, text.length(), true, 800, measureWidth);
        canvas.drawText("w:" + measureWidth[0] + " c:" + measuredCount, 0, 650, paint);
        canvas.drawText(text, 0, measuredCount, 130, 650, mPaint);

        //计算光标的X值，API 23才引入
        /*
        对于一段文字，计算出某个字符处光标的 x 坐标。
        start end 是文字的起始和结束坐标；
        contextStart contextEnd 是上下文的起始和结束坐标；
        isRtl 是文字的方向；
        offset 关键参数，指字数的偏移，即计算第几个字符处的光标。

        其实，说是测量光标位置的，本质上这也是一个测量文字宽度的方法。
        上面这个例子中，start 和 contextStart 都是 0，
        end contextEnd 和 offset 都等于 text.length()。
        在这种情况下，它是等价于 measureText(text) 的，即完整测量一段文字的宽度。
        而对于更复杂的需求，getRunAdvance() 能做的事就比 measureText() 多了。

        比如特殊字符，不采用光标得到的数值可能在符号表情的中间，导致光标插入到表情中，而getRunAdvance不会。
         */
        float advance = mPaint.getRunAdvance(text, 0, text.length(), 0, text.length(), false, text.length() - 1);
        canvas.drawLine(130 + advance, 650-70, 130 + advance, 650 + 5, mPaint);

        /*
            getOffsetForAdvance给出一个位置的像素值，计算出文字中最接近这个位置的字符偏移量（即第几个字符最接近这个坐标）。
            即第几个字符与它接近，得出的字符数再用getRunAdvance计算出光标位置，即得出点击的位置光标应该落在哪里
         */
        int offsetForAdvance = mPaint.getOffsetForAdvance(text, 0, text.length(), 0, text.length(), false, 100);
        float advance2 = mPaint.getRunAdvance(text, 0, text.length(), 0, text.length(), false, offsetForAdvance);
        canvas.drawLine(130 + advance2, 650-70, 130 + advance2, 650 + 5, mPaint);

        //检查指定的字符串中是否是一个单独的字形 (glyph）
        mPaint.hasGlyph("a");      //true
        mPaint.hasGlyph("b");       //true
        mPaint.hasGlyph("ab");      //false
        mPaint.hasGlyph("\uD83C\uDDE8\uD83C\uDDF3"); //true

    }
}
