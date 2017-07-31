package com.lethalskillzz.baker.presentation.recipestep;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lethalskillzz.baker.data.model.Step;
import com.lethalskillzz.baker.utils.AppConstants;
import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.baker.presentation.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepActivity extends BaseActivity {

    @BindView(R.id.step_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.step_tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.step_viewpager)
    ViewPager mViewPager;

    private int mStepId;
    private List<Step> mSteps = new ArrayList<>();

    public static Intent getStartIntent(Context context, int stepId, List<Step> steps) {
        Intent intent = new Intent(context, RecipeStepActivity.class);
        intent.putExtra(AppConstants.BUNDLE_STEP_ID, stepId);
        intent.putParcelableArrayListExtra(AppConstants.BUNDLE_STEP_DATA, (ArrayList<? extends Parcelable>) steps);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mStepId = getIntent().getIntExtra(AppConstants.BUNDLE_STEP_ID, AppConstants.BUNDLE_DEFAULT_ID);
        mSteps = getIntent().getExtras().getParcelableArrayList(AppConstants.BUNDLE_STEP_DATA);

        setUp();
    }



    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
            mToolbar.setTitle(R.string.recipes_label);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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
            mTabLayout.setVisibility(View.GONE);
        }

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return RecipeStepFragment.newInstance(mSteps.get(position));
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
