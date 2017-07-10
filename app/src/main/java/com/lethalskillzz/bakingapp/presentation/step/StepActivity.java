package com.lethalskillzz.bakingapp.presentation.step;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;

import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.RECIPE_ID;
import static com.lethalskillzz.bakingapp.utils.AppConstants.STEP_ID;

public class StepActivity extends BaseActivity {


    public static Intent getStartIntent(Context context, int recipeId, int stepId) {
        Intent intent = new Intent(context, StepActivity.class);
        intent.putExtra(RECIPE_ID, recipeId);
        intent.putExtra(STEP_ID, stepId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

    }


    @Override
    protected void setUp() {

    }
}
