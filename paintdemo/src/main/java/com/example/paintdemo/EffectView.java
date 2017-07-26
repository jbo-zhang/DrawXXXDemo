package com.example.paintdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangjinbo on 17-7-26.
 */

public class EffectView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public EffectView(Context context) {
        this(context, null);
    }

    public EffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pretty_girl);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,100,mPaint);



        //平头    默认为该值
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100,400,400,400, mPaint);
        //方头
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100,500,400,500, mPaint);
        //圆头
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100,600,400,600, mPaint);

        //DashPathEffect 用来画虚线
        /*
            DashPathEffect(float[] intervals, float phase) 中，
            第一个参数 intervals 是一个数组，它指定了虚线的格式：
            数组中元素必须为偶数（最少是 2 个），
            按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
            例如上面代码中的 20, 5, 10, 5 就表示虚线是按照
            「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
            第二个参数 phase 是虚线的偏移量。
         */
        PathEffect dashPathEffect = new DashPathEffect(new float[]{20,10,5,10}, 0);
        mPaint.setPathEffect(dashPathEffect);
        mPaint.setStrokeWidth(0);
        canvas.drawLine(100,350,100,650,mPaint);


        mPaint.setPathEffect(null);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.BUTT);

        //尖角 默认为该值
        mPaint.setStrokeJoin(Paint.Join.MITER);
        Path pathMiter = new Path();
        pathMiter.moveTo(500, 100);
        pathMiter.lineTo(600, 100);
        pathMiter.lineTo(520,200);
        canvas.drawPath(pathMiter, mPaint);

        //平角
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        Path pathBevel = new Path();
        pathBevel.moveTo(650, 100);
        pathBevel.lineTo(750, 100);
        pathBevel.lineTo(670,200);
        canvas.drawPath(pathBevel, mPaint);

        //圆角
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        Path pathRound = new Path();
        pathRound.moveTo(800, 100);
        pathRound.lineTo(900, 100);
        pathRound.lineTo(820,200);
        canvas.drawPath(pathRound, mPaint);


        mPaint.setStrokeJoin(Paint.Join.MITER);
        //对尖角的补偿
        mPaint.setStrokeMiter(0);
        Path pathStrokeMiter1 = new Path();
        pathStrokeMiter1.moveTo(500, 300);
        pathStrokeMiter1.lineTo(600, 300);
        pathStrokeMiter1.lineTo(520,400);
        canvas.drawPath(pathStrokeMiter1, mPaint);

        mPaint.setStrokeJoin(Paint.Join.MITER);
        //对尖角的补偿
        mPaint.setStrokeMiter(0);
        Path pathStrokeMiter2 = new Path();
        pathStrokeMiter2.moveTo(700, 300);
        pathStrokeMiter2.lineTo(800, 300);
        pathStrokeMiter2.lineTo(800,400);
        canvas.drawPath(pathStrokeMiter2, mPaint);

        /*
            以上就是 Paint 的两个色彩优化的方法： setDither(dither)，
            设置抖动来优化色彩深度降低时的绘制效果； setFilterBitmap(filterBitmap)，
            设置双线性过滤来优化 Bitmap 放大绘制的效果
        */
        mPaint.setFilterBitmap(true);
        canvas.drawBitmap(mBitmap, 0,0, mPaint);

        //增加抖动
        mPaint.setDither(true);
        canvas.drawBitmap(mBitmap, 600,0, mPaint);


        Path pathEffectTestPath = new Path();
        pathEffectTestPath.moveTo(500,500);
        pathEffectTestPath.lineTo(600,600);
        pathEffectTestPath.lineTo(700,550);
        pathEffectTestPath.lineTo(800,620);
        pathEffectTestPath.lineTo(900,500);

        mPaint.setStrokeWidth(0);

        //把拐角变为圆角
        /*
            它的构造方法 CornerPathEffect(float radius) 的参数 radius 是圆角的半径。
         */
        PathEffect cornerPathEffect = new CornerPathEffect(10);

        //把线条进行随机的偏离
        /*
            它的构造方法 DiscretePathEffect(float segmentLength, float deviation)
            的两个参数中，segmentLength 是用来拼接的每个线段的长度， deviation 是偏离量
         */
        PathEffect discretePathEffect = new DiscretePathEffect(20, 5);

        Path dashPath = new Path();
        dashPath.lineTo(10,10);
        dashPath.lineTo(20,0);
        dashPath.close();

        //它是使用一个 Path 来绘制「虚线」
        /*
            PathDashPathEffect.Style ，是一个 enum ，具体有三个值：
            TRANSLATE：位移
            ROTATE：旋转
            MORPH：变体
         */
        PathEffect pathDashPathEffect = new PathDashPathEffect(dashPath, 40, 0, PathDashPathEffect.Style.MORPH);

        //组合，分别按照两种 PathEffect 分别对目标进行绘制。
        PathEffect sumPathEffect = new SumPathEffect(dashPathEffect, discretePathEffect);

        //混合, 先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
        /*
            它的构造方法 ComposePathEffect(PathEffect outerpe, PathEffect innerpe) 中的两个 PathEffect 参数，
            innerpe 是先应用的， outerpe 是后应用的。
            所以上面的代码就是「先偏离，再变虚线」。
            而如果把两个参数调换，就成了「先变虚线，再偏离」
         */
//        PathEffect composePathEffect = new ComposePathEffect(dashPathEffect, discretePathEffect);
        PathEffect composePathEffect = new ComposePathEffect(discretePathEffect, dashPathEffect);

        mPaint.setPathEffect(composePathEffect);
        canvas.drawPath(pathEffectTestPath, mPaint);

        mPaint.setPathEffect(null);
        mPaint.setStyle(Paint.Style.FILL);
        /*
        效果就是上面这样。方法的参数里，
        radius 是阴影的模糊范围； dx dy 是阴影的偏移量；
        shadowColor 是阴影的颜色。
        如果要清除阴影层，使用 clearShadowLayer() 。
         */
        //在之后的绘制内容下面加一层阴影
        mPaint.setShadowLayer(10,0,0, Color.RED);
        mPaint.setTextSize(40);
        canvas.drawText("Hello HenCoder", 150, 50, mPaint);

        mPaint.clearShadowLayer();


        /*
            为之后的绘制设置 MaskFilter。上一个方法 setShadowLayer()
            是设置的在绘制层下方的附加效果；
            而这个 MaskFilter 和它相反，设置的是在绘制层上方的附加效果。
         */
        canvas.drawColor(Color.GRAY);
        canvas.scale(0.4f, 0.4f);

        //模糊效果的 MaskFilter。
        /*
        它的构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中，
            radius 参数是模糊的范围， style 是模糊的类型。一共有四种：
            NORMAL: 内外都模糊绘制
            SOLID: 内部正常绘制，外部模糊
            INNER: 内部模糊，外部不绘制
            OUTER: 内部不绘制，外部模糊（什么鬼？）
         */
        //mPaint.setMaskFilter(new BlurMaskFilter(100,BlurMaskFilter.Blur.INNER));

        //浮雕效果的 MaskFilter。
        /*
        它的构造方法EmbossMaskFilter(float[] direction, float ambient, float specular, float blurRadius) 的参数里，
            direction 是一个 3 个元素的数组，指定了光源的方向；
            ambient 是环境光的强度，数值范围是 0 到 1；
            specular 是炫光的系数；
            blurRadius 是应用光线的范围。
         */
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{0,1,1}, 0.2f, 8, 10));


        canvas.drawBitmap(mBitmap, 100, 100, mPaint);

        //获取绘制的 Path
        /*
        getFillPath(Path src, Path dst)
            默认情况下（线条宽度为 0、没有 PathEffect），原 Path 和实际 Path 是一样的；
            而在线条宽度不为 0 （并且模式为 STROKE 模式或 FLL_AND_STROKE ），
            或者设置了 PathEffect 的时候，实际 Path 就和原 Path 不一样了
         */
        /*
        getTextPath(String text, int start, int end, float x, float y, Path path)
        / getTextPath(char[] text, int index, int count, float x, float y, Path path)
        这里就回答第二个问题：「文字的 Path」。
        文字的绘制，虽然是使用 Canvas.drawText() 方法，
        但其实在下层，文字信息全是被转化成图形，对图形进行绘制的。
        getTextPath() 方法，获取的就是目标文字所对应的 Path 。
        这个就是所谓「文字的 Path」。
         */

        //mPaint.getFillPath(src, dst);

       //mPaint.getTextPath(String text, int start, int end, float x, float y, Path path)


    }
}
