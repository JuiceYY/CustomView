package cn.istary.customview.fragment;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/30 21:53
 * DESCRIPTION:
 */

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class TranslateAnimFragment extends BasicAnimatorFragment {
    @Override
    protected void startAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 500);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
