package cn.istary.customview.fragment.ball;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.istary.customview.R;
import cn.istary.customview.widget.FreeFallBallView;
import cn.istary.customview.widget.SimpleMoveBallView;

/*
 * CREATED BY: Sinry
 * TIME: 2019/4/2 23:23
 * DESCRIPTION:
 */

public class FreeFallBallFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "FreeFallBallFragment";

    private Button mBtnStart;
    private Button mBtnStop;
    private FreeFallBallView mFreeFallBallView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free_fall_ball, container, false);
        mBtnStart = view.findViewById(R.id.btn_start_anim);
        mBtnStop = view.findViewById(R.id.btn_stop_anim);
        mFreeFallBallView = view.findViewById(R.id.view_free_fall_ball);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_anim:
                mFreeFallBallView.setStartAnim(true);
                break;
            case R.id.btn_stop_anim:
                mFreeFallBallView.setStartAnim(false);
                break;
        }
    }

}
