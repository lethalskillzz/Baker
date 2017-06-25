package com.lethalskillzz.bakingapp.presentation.ingredient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class IngredientActivity extends BaseActivity implements IngredientMvpView {

    @Inject
    IngredientMvpPresenter<IngredientMvpView> mPresenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, IngredientActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(IngredientActivity.this);
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
