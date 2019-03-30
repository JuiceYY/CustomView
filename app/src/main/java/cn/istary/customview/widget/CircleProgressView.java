package cn.istary.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/30 23:41
 * DESCRIPTION:
 */

public class CircleProgressView extends View {

    private int mWidth, mHeight;
    private float mRadius;
    private int mProgress = 0; //0-100

    private Paint mPaint;
    private Paint mTextPaint;

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public CircleProgressView(Context context) {
        super(context);
        init();
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(100);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = Math.min(w,h) >> 2;
        mTextPaint.setTextSize(mRadius/3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth>>1, mHeight>>1);
        canvas.drawText(mProgress+"%", 0, 0, mTextPaint);//文字需要居中对齐
        canvas.rotate(90);
        //这里如果useCenter设为true会把半径线画出来
        canvas.drawArc(-mRadius, -mRadius, mRadius, mRadius, 0f, 360*(float)(mProgress/100f), false, mPaint);
    }
}
