package com.lethalskillzz.bakingapp.presentation.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;
import com.lethalskillzz.bakingapp.presentation.step.StepFragment;
import com.lethalskillzz.bakingapp.utils.FragmentUtils;

import butterknife.BindBool;
import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.DEFAULT_RECIPE_ID;
import static com.lethalskillzz.bakingapp.utils.AppConstants.RECIPE_ID;

public class RecipeDetailActivity extends BaseActivity  {


    @BindBool(R.bool.master_detail_mode)
    boolean masterDetaileMode;


    public static Intent getStartIntent(Context context, int recipeId) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_ID, recipeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        setUnBinder(ButterKnife.bind(this));

        setUp();

        int recipeId = getIntent().getIntExtra(RECIPE_ID, DEFAULT_RECIPE_ID);

        RecipeDetailFragment recipeDetailFragment =
                (RecipeDetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.master_fragment_container);

        if (recipeDetailFragment == null) {
            recipeDetailFragment = RecipeDetailFragment.newInstance(recipeId);
            FragmentUtils.addFragmentTo(getSupportFragmentManager(), recipeDetailFragment,
                    R.id.master_fragment_container);

            if (masterDetaileMode) {

                StepFragment stepFragment = StepFragment.newInstance(recipeId);
                FragmentUtils.addFragmentTo(getSupportFragmentManager(), stepFragment,
                        R.id.detail_fragment_container);
            }
        }

    }

    @Override
    protected void setUp() {
        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}
