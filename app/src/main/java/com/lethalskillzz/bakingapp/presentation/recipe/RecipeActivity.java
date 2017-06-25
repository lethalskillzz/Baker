package com.lethalskillzz.bakingapp.presentation.recipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class RecipeActivity extends BaseActivity implements RecipeMvpView {

    @Inject
    RecipeMvpPresenter<RecipeMvpView> mPresenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RecipeActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(RecipeActivity.this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
