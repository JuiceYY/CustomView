package cn.istary.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * DESCRIPTION:
 *
 * @author Sinry
 * @version 2019/4/29 15:45
 */

public class PendulumBallView extends View {

    private static final String TAG = "PendulumBallView";

    private Ball mBall;
    private int mWidth, mHeight;
    private Paint mBallPaint;
    private Paint mLinePaint;

    private float mDegree;

    private boolean isAnimStart = false;

    public PendulumBallView(Context context) {
        super(context);
        init();
    }

    public PendulumBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PendulumBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    private void init() {

        //垂直方向向左偏30度, Android坐标系顺时针为正
        mDegree = (float) (Math.PI / 6);

        int s = mHeight / 3;
        int x = - (int) (s * Math.sin(mDegree));
        int y = (int) (s * Math.cos(mDegree));

        mBall = new Ball.Builder()
                .setX(x)
                .setY(y)
                .setRadius(40)
                .build();

        mBallPaint = new Paint();
        mBallPaint.setStyle(Paint.Style.FILL);
        mBallPaint.setAntiAlias(true);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.rgb(0, 0, 255));
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mBallPaint.setColor(mBall.getColor());
        canvas.translate(mWidth>>1, mHeight/3);
        canvas.drawCircle(mBall.x, mBall.y, mBall.getRadius(), mBallPaint);
        canvas.drawLine(0, 0, mBall.x, mBall.y, mLinePaint);
        canvas.restore();

        Log.d(TAG, "onDraw: draw completed");

    }

    //在这个方法中更新小球位置, 给ValueAnimator设置AnimatorUpdateListener, onAnimationUpdate方法中调用updateBall
    public void updateBall(float deg) {
        this.mDegree = deg;


        int s = mHeight / 3;
        int x = - (int) (s * Math.sin(mDegree));
        int y = (int) (s * Math.cos(mDegree));

        mBall.x = x;
        mBall.y = y;
    }
}
