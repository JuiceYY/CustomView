package cn.istary.customview.activity;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.istary.customview.R;
import cn.istary.customview.widget.CircleProgressView;

public class CircleProgressActivity extends AppCompatActivity implements View.OnClickListener{

    private CircleProgressView mCircleProgressView;
    private Button mStartButton;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        mCircleProgressView = findViewById(R.id.circle_progress_view);
        mStartButton = findViewById(R.id.btn_start_progress);
        mResetButton = findViewById(R.id.btn_reset_progress);
        mStartButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_progress:
                ObjectAnimator animator = ObjectAnimator.ofObject(mCircleProgressView, "progress", new IntEvaluator(), 0, 100);
                animator.setDuration(2000);
                animator.start();
                break;
            case R.id.btn_reset_progress:
                mCircleProgressView.setProgress(0);
                break;
        }
    }
}
