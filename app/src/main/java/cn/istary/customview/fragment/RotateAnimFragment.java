package cn.istary.customview.fragment;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/30 22:12
 * DESCRIPTION:
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class RotateAnimFragment extends BasicAnimatorFragment {
    @Override
    protected void startAnim(View view) {
        ObjectAnimator translation1 = ObjectAnimator.ofFloat(view, "translationX", 400);
        ObjectAnimator translation2 = ObjectAnimator.ofFloat(view, "translationY", 400);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 90f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.play(translation1).with(translation2).before(rotate);
        set.start();
    }
}
