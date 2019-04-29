package cn.istary.customview.fragment.ball;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.istary.customview.R;
import cn.istary.customview.widget.SimpleMoveBallView;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/31 17:17
 * DESCRIPTION:
 */

public class SimpleMoveBallFragment extends Fragment implements View.OnClickListener{

    private Button mBtnStart;
    private Button mBtnStop;
    private SimpleMoveBallView mSimpleMoveBallView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_move_ball, container, false);
        mBtnStart = view.findViewById(R.id.btn_start_anim);
        mBtnStop = view.findViewById(R.id.btn_stop_anim);
        mSimpleMoveBallView = view.findViewById(R.id.view_simple_move_ball);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_anim:
                mSimpleMoveBallView.setStartAnim(true);
                break;
            case R.id.btn_stop_anim:
                mSimpleMoveBallView.setStartAnim(false);
                break;
        }
    }
}
