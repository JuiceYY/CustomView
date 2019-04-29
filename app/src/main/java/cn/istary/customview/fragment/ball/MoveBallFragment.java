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
import cn.istary.customview.widget.AbstractBallView;

/*
 * CREATED BY: Sinry
 * TIME: 2019/4/2 23:31
 * DESCRIPTION: 小球运动的fragment
 */

public class MoveBallFragment extends Fragment implements View.OnClickListener{

    protected Button mBtnStart;
    protected Button mBtnStop;
    protected AbstractBallView mView;

    protected int mLayoutId;
    protected int mViewId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(mLayoutId, container, false);
        mBtnStart = view.findViewById(R.id.btn_start_anim);
        mBtnStop = view.findViewById(R.id.btn_stop_anim);
        mView = view.findViewById(mViewId);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_anim:
                mView.setStartAnim(true);
                break;
            case R.id.btn_stop_anim:
                mView.setStartAnim(false);
                break;
        }
    }

}
