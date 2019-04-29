package cn.istary.customview.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.istary.customview.R;
import cn.istary.customview.fragment.PendulumBallFragment;
import cn.istary.customview.fragment.ball.FreeFallBallFragment;
import cn.istary.customview.fragment.ball.SimpleMoveBallFragment;
import cn.istary.customview.widget.AbstractBallView;
import cn.istary.customview.widget.FreeFallBallView;
import cn.istary.customview.widget.SimpleMoveBallView;

public class MoveBallActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<Fragment> mBallViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_ball);

        init();
    }

    private void init() {

        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);

        mTitles = new ArrayList<>();
        mTitles.add("匀速反弹");
        mTitles.add("自由落体");
        mTitles.add("钟摆运动");

        mBallViews = new ArrayList<>();
        mBallViews.add(new SimpleMoveBallFragment());
        mBallViews.add(new FreeFallBallFragment());
        mBallViews.add(new PendulumBallFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mBallViews.get(i);
            }

            @Override
            public int getCount() {
                return mBallViews.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
