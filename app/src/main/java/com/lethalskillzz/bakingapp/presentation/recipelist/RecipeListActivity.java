package com.lethalskillzz.bakingapp.presentation.recipelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.CoordinatorLayout;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.idlingresource.RecipesIdlingResource;
import com.lethalskillzz.bakingapp.data.model.Recipe;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;
import com.lethalskillzz.bakingapp.presentation.view.MarginDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends BaseActivity implements RecipeListMvpView {

    @Inject
    RecipeListMvpPresenter<RecipeListMvpView> mPresenter;

    @Nullable
    private RecipesIdlingResource idlingResource;

    private RecipeListAdapter recipeListAdapter;

    @BindView(R.id.recipe_list_toolbar)
    Toolbar toolbar;
    @BindView(R.id.recipe_list_coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recipe_list_recycler_view)
    RecyclerView recipeListRecyclerView;
    @BindView(R.id.recipe_list_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.refresh_text)
    TextView refreshText;

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

        recipeListAdapter = new RecipeListAdapter(
                new ArrayList<>(0),
                recipeId -> mPresenter.openRecipeDetails(recipeId)
        );

        recipeListAdapter.setHasStableIds(true);

        setUp();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
            getSupportActionBar().setTitle(R.string.recipes_label);
        }

        recipeListRecyclerView.addItemDecoration(new MarginDecoration(this));
        recipeListRecyclerView.setHasFixedSize(true);
        recipeListRecyclerView.setAdapter(recipeListAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryLight);
        mSwipeRefreshLayout.setOnRefreshListener(()->mPresenter.loadRecipes(idlingResource));
    }


    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipeListAdapter.refreshRecipeList(recipes);
    }


    @Override
    public void showRecipeDetails(int recipeId) {
        //startActivity(RecipeDetailActivity.p(this, recipeId));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RecipesIdlingResource();
        }
        return idlingResource;
    }
}
