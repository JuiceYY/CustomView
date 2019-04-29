package cn.istary.customview.widget;
/*
 * CREATED BY: Sinry
 * TIME: 2019/4/2 23:00
 * DESCRIPTION: 自由落体
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;

public class FreeFallBallView extends AbstractBallView {

    private static final String TAG = "FreeFallBallView";

    public FreeFallBallView(Context context) {
        super(context);
    }

    public FreeFallBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FreeFallBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initBalls() {
        mBalls = new ArrayList<>();

        Ball ball = new Ball.Builder()
                .setRadius(40)
                .setVY(0)
                .setVX(0)
                .setAY(1)
                .setY(-(mHeight >> 1))
                .build();

        mBalls.add(ball);
    }

    @Override
    protected void updateBall() {
        for (Ball ball : mBalls) {

            Log.d(TAG, "updateBall: speed = " + ball.getVY() + " y = " + ball.y);

            ball.y += ball.getVY();

            if (ball.y > mHeight / 2) {
                ball.turnY();
                ball.setColor(randomRGB());
                continue;
            }
            if (ball.y < -(mHeight / 2)) {
                ball.turnY();
                ball.setColor(randomRGB());
                continue;
            }

            ball.setVY(ball.getVY() + ball.getAY());
        }
    }
}
