package cn.istary.customview.fragment;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/30 22:20
 * DESCRIPTION:
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleAnimFragment extends BasicAnimatorFragment {
    @Override
    protected void startAnim(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 2f);
        ObjectAnimator translation1 = ObjectAnimator.ofFloat(view, "translationX", 400);
        ObjectAnimator translation2 = ObjectAnimator.ofFloat(view, "translationY", 400);
        AnimatorSet set = new AnimatorSet();
        set.play(translation1).with(translation2);
        set.start();
        AnimatorSet set1 = new AnimatorSet();
        set1.play(scaleX).with(scaleY);
        set1.start();
    }
}
