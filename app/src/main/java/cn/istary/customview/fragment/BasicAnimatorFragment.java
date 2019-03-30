package cn.istary.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import cn.istary.customview.R;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/30 21:36
 * DESCRIPTION:
 */

public abstract class BasicAnimatorFragment extends Fragment implements View.OnClickListener{

    protected Button mBtnStart;
    protected Button mBtnCancel;
    protected ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_animator, container, false);
        mBtnStart = view.findViewById(R.id.btn_start_anim);
        mBtnCancel = view.findViewById(R.id.btn_cancel_anim);
        mImageView = view.findViewById(R.id.imageview);

        mBtnCancel.setOnClickListener(this);
        mBtnStart.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_anim:
                startAnim(mImageView);
                break;
            case R.id.btn_cancel_anim:

                break;
        }
    }

    protected abstract void startAnim(View view);
}
