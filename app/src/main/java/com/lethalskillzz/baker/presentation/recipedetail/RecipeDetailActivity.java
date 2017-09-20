package com.lethalskillzz.baker.presentation.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.lethalskillzz.baker.utils.AppConstants;
import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.baker.presentation.base.BaseActivity;
import com.lethalskillzz.baker.utils.AppLogger;
import com.lethalskillzz.baker.utils.FragmentUtils;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends BaseActivity  {

    @BindView(R.id.recipe_detail_toolbar)
    Toolbar mToolbar;

    @BindBool(R.bool.master_detail_mode)
    boolean masterDetailMode;

    RecipeDetailFragment recipeDetailFragment;

    public static Intent getStartIntent(Context context, int recipeId) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(AppConstants.BUNDLE_RECIPE_ID, recipeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        setUp();

        int recipeId = getIntent().getIntExtra(AppConstants.BUNDLE_RECIPE_ID, AppConstants.BUNDLE_DEFAULT_ID);

        recipeDetailFragment =
                (RecipeDetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.master_fragment_container);

        if (recipeDetailFragment != null) {
            AppLogger.e("if");
                recipeDetailFragment = (RecipeDetailFragment) getSupportFragmentManager()
                        .getFragment(savedInstanceState, AppConstants.BUNDLE_FRAGMENT_KEY);
        } else {
            AppLogger.e("else");
            recipeDetailFragment = RecipeDetailFragment.newInstance(recipeId);
            FragmentUtils.addFragmentTo(getSupportFragmentManager(), recipeDetailFragment,
                    R.id.master_fragment_container);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (recipeDetailFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, AppConstants.BUNDLE_FRAGMENT_KEY, recipeDetailFragment);
        }

    }


    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
            mToolbar.setTitle(R.string.recipes_label);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }


}
