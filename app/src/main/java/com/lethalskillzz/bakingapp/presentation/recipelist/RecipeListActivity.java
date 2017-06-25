package com.lethalskillzz.bakingapp.presentation.recipelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class RecipeListActivity extends BaseActivity implements RecipeListMvpView {

    @Inject
    RecipeListMvpPresenter<RecipeListMvpView> mPresenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RecipeListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(RecipeListActivity.this);
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
