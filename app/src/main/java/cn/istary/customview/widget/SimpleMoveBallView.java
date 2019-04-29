package cn.istary.customview.widget;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/31 16:58
 * DESCRIPTION:
 */

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

public class SimpleMoveBallView extends AbstractBallView {

    public SimpleMoveBallView(Context context) {
        super(context);
    }

    public SimpleMoveBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleMoveBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initBalls() {
        mBalls = new ArrayList<>();

        Ball ball = new Ball.Builder()
                .setRadius(80)
                .setVY(25)
                .setVX(30)
                .build();

        mBalls.add(ball);
    }

    @Override
    protected void updateBall() {
        for (Ball ball: mBalls){
            ball.x += ball.getVX();
            ball.y += ball.getVY();
            int maxX = mWidth >> 1, maxY = mHeight >> 1;
            if(ball.x > maxX-ball.getRadius() || ball.x < -maxX + ball.getRadius()){
                ball.turnX();
                ball.setColor(randomRGB());
                continue;
            }
            if(ball.y > maxY-ball.getRadius() || ball.y < -maxY+ball.getRadius()){
                ball.turnY();
                ball.setColor(randomRGB());
                continue;
            }
        }
    }


}
