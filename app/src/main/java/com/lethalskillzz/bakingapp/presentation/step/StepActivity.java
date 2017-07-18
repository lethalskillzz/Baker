package com.lethalskillzz.bakingapp.presentation.step;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_DEFAULT_ID;
import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_STEP_DATA;
import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_STEP_ID;

public class StepActivity extends BaseActivity {

    @BindView(R.id.step_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.step_tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.step_viewpager)
    ViewPager mViewPager;

    private int mStepId;
    private List<Step> mSteps = new ArrayList<>();

    public static Intent getStartIntent(Context context, int stepId, List<Step> steps) {
        Intent intent = new Intent(context, StepActivity.class);
        intent.putExtra(BUNDLE_STEP_ID, stepId);
        intent.putParcelableArrayListExtra(BUNDLE_STEP_DATA, (ArrayList<? extends Parcelable>) steps);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mStepId = getIntent().getIntExtra(BUNDLE_STEP_ID, BUNDLE_DEFAULT_ID);
        mSteps = getIntent().getExtras().getParcelableArrayList(BUNDLE_STEP_DATA);


        setUp();
    }


    @Override
    protected void setUp() {

        for(Step step : mSteps) {
            if(step.id() == 0)
            {
                mTabLayout.addTab(mTabLayout.newTab().setText("Intro"));
            }
            else {
                mTabLayout.addTab(mTabLayout.newTab().setText(
                        String.format(getString(R.string.step_number_format), (step.id()))));
            }
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Fullscreen mode for non-tablet landscape orientation
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mToolbar.setVisibility(View.GONE);
            mViewPager.setVisibility(View.GONE);
        }

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return StepFragment.newInstance(mSteps.get(position));
            }
            @Override
            public int getCount() {
                return mSteps.size();
            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        mViewPager.setCurrentItem(mStepId);
    }

}
