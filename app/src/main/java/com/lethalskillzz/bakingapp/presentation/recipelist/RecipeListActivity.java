package com.lethalskillzz.bakingapp.presentation.recipelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.CoordinatorLayout;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.idlingresource.RecipesIdlingResource;
import com.lethalskillzz.bakingapp.data.model.Recipe;
import com.lethalskillzz.bakingapp.presentation.base.BaseActivity;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailActivity;
import com.lethalskillzz.bakingapp.presentation.view.MarginDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.RECIPE_KEY;

public class RecipeListActivity extends BaseActivity implements
        RecipeListMvpView, RecipeListAdapter.Callback {

    @Inject
    RecipeListMvpPresenter<RecipeListMvpView> mPresenter;

    @Inject
    RecipeListAdapter mRecipeListAdapter;

    @Nullable
    private RecipesIdlingResource idlingResource;

    private List<Recipe> recipes;

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

    @BindInt(R.integer.grid_column_count)
    int gridColumnCount;

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

        recipes = new ArrayList<>();

        mRecipeListAdapter.setCallback(this);
        mRecipeListAdapter.setHasStableIds(true);

        setUp();

        if (savedInstanceState != null) {

            if (savedInstanceState.containsKey(RECIPE_KEY)) {
                recipes = savedInstanceState.getParcelableArrayList(RECIPE_KEY);
                showRecipeList(recipes);
            }
        } else {
            refreshRecipe();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (recipes != null) {
            outState.putParcelableArrayList(RECIPE_KEY, (ArrayList<? extends Parcelable>) recipes);
        }
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

        GridLayoutManager layoutManager = new GridLayoutManager(this, gridColumnCount);
        recipeListRecyclerView.setLayoutManager(layoutManager);
        recipeListRecyclerView.setHasFixedSize(true);
        recipeListRecyclerView.addItemDecoration(new MarginDecoration(this));
        recipeListRecyclerView.setAdapter(mRecipeListAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryLight);
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshRecipe);
    }


    @Override
    public void showRecipeList(List<Recipe> recipes) {
        this.recipes = recipes;
        mRecipeListAdapter.refreshRecipeList(recipes);
    }


    @Override
    public void showRecipeDetail(int recipeId) {
        startActivity(RecipeDetailActivity.getStartIntent(this, recipeId));
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    void refreshRecipe() {
        if (isNetworkConnected()) {

            refreshText.setVisibility(View.GONE);
            mPresenter.loadRecipes(true, idlingResource);

        } else {

            mPresenter.loadRecipes(false, idlingResource);
            hideLoading();
            showMessage(getString(R.string.error_no_internet));
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RecipesIdlingResource();
        }
        return idlingResource;
    }

    @Override
    public void onRecipeListClick(int recipeId) {
        mPresenter.openRecipeDetails(recipeId);
    }
}
