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
import cn.istary.customview.fragment.BasicAnimatorFragment;
import cn.istary.customview.fragment.RotateAnimFragment;
import cn.istary.customview.fragment.ScaleAnimFragment;
import cn.istary.customview.fragment.TranslateAnimFragment;

public class BasicAnimatorActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<BasicAnimatorFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_animator);
        init();
    }

    private void init() {

        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);

        mTitles = new ArrayList<>();
        mTitles.add("平移");
        mTitles.add("旋转");
        mTitles.add("缩放");

        mFragments = new ArrayList<>();
        mFragments.add(new TranslateAnimFragment());
        mFragments.add(new RotateAnimFragment());
        mFragments.add(new ScaleAnimFragment());

        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
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
