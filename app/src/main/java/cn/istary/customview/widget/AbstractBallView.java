package cn.istary.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Random;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/31 12:49
 * DESCRIPTION:
 */

public abstract class AbstractBallView extends View {

    protected int mWidth, mHeight;
    protected List<Ball> mBalls;
    protected Paint mPaint;

    protected boolean mStartAnim = false;

    public AbstractBallView(Context context) {
        super(context);
        init();
    }

    public AbstractBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AbstractBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void init() {
//        mBall = new Ball();
//        mBall.setVY(20);
//        mBall.setRadius(100);
        initBalls();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    protected abstract void initBalls();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        for(Ball ball: mBalls){
            mPaint.setColor(ball.getColor());
            canvas.translate(mWidth>>1, mHeight>>1);
            canvas.drawCircle(ball.x, ball.y, ball.getRadius(), mPaint);
        }

        canvas.restore();

        if(mStartAnim){
            updateBall();
            invalidate();
        }
    }

//    private void updateBall() {
//        mBall.x += mBall.getVX();
//        mBall.y += mBall.getVY();
//        int maxX = mWidth >> 1, maxY = mHeight >> 1;
//        if(mBall.x > maxX-mBall.getRadius() || mBall.x < -maxX + mBall.getRadius()){
//            mBall.turnX();
//            mBall.setColor(randomRGB());
//        }
//        if(mBall.y > maxY-mBall.getRadius() || mBall.y < -maxY+mBall.getRadius()){
//            mBall.turnY();
//            mBall.setColor(randomRGB());
//        }
//    }

    protected abstract void updateBall();

    protected int randomRGB() {
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                mStartAnim = true;
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                mStartAnim = false;
//                invalidate();
//                break;
//        }
//        return true; //消费事件
//    }

    //对外提供接口
    public void setStartAnim(boolean b){
        this.mStartAnim = b;
        invalidate();
    }
}
