package cn.istary.customview.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import cn.istary.customview.R;
import cn.istary.customview.widget.PendulumBallView;

/**
 * DESCRIPTION:
 *
 * @author Sinry
 * @version 2019/4/29 17:16
 */

public class PendulumBallFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "PendulumBallFragment";

    private PendulumBallView mBallView;
    private Button mBtnStart;
    private Button mBtnStop;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pendulum_ball, container, false);
        mBallView = view.findViewById(R.id.view_pendulum_ball);
        mBtnStart = view.findViewById(R.id.btn_start_anim);
        mBtnStop = view.findViewById(R.id.btn_stop_anim);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_anim:
                startAnim();
                break;

            case R.id.btn_stop_anim:
                stopAnim();
                break;

            default:
                break;
        }
    }

    private void stopAnim() {

    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(-0.5f, 0.5f);
        animator.setRepeatCount(100);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float deg = (float) ((float)animation.getAnimatedValue() * 3.14 / 3);
                Log.d(TAG, "onAnimationUpdate: deg = "+deg);
                mBallView.updateBall(deg);
                mBallView.invalidate();
            }
        });
        animator.start();
    }
}
