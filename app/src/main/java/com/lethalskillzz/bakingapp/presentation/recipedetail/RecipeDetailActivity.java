package com.lethalskillzz.bakingapp.presentation.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;
import com.lethalskillzz.bakingapp.utils.AppLogger;
import com.lethalskillzz.bakingapp.utils.FragmentUtils;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_DEFAULT_ID;
import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_FRAGMENT_KEY;
import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_RECIPE_ID;

public class RecipeDetailActivity extends BaseActivity  {

    @BindView(R.id.recipe_detail_toolbar)
    Toolbar mToolbar;

    @BindBool(R.bool.master_detail_mode)
    boolean masterDetailMode;

    RecipeDetailFragment recipeDetailFragment;

    public static Intent getStartIntent(Context context, int recipeId) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(BUNDLE_RECIPE_ID, recipeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        setUp();

        int recipeId = getIntent().getIntExtra(BUNDLE_RECIPE_ID, BUNDLE_DEFAULT_ID);

        recipeDetailFragment =
                (RecipeDetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.master_fragment_container);

        if (recipeDetailFragment != null) {
            AppLogger.e("if");
                recipeDetailFragment = (RecipeDetailFragment) getSupportFragmentManager()
                        .getFragment(savedInstanceState, BUNDLE_FRAGMENT_KEY);
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
            getSupportFragmentManager().putFragment(outState, BUNDLE_FRAGMENT_KEY, recipeDetailFragment);
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
