package com.lethalskillzz.bakingapp.presentation.recipedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.di.component.ActivityComponent;
import com.lethalskillzz.bakingapp.presentation.base.BaseFragment;
import com.lethalskillzz.bakingapp.presentation.recipestep.RecipeStepActivity;
import com.lethalskillzz.bakingapp.presentation.recipestep.RecipeStepFragment;
import com.lethalskillzz.bakingapp.utils.FragmentUtils;
import com.lethalskillzz.bakingapp.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_RECIPE_ID;

/**
 * Created by ibrahimabdulkadir on 06/07/2017.
 */

public class RecipeDetailFragment extends BaseFragment implements
        RecipeDetailMvpView, RecipeDetailAdapter.Callback {

    @Inject
    RecipeDetailMvpPresenter<RecipeDetailMvpView> mPresenter;

    @Inject
    RecipeDetailAdapter mRecipeDetailAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recipe_details_ingredients)
    TextView recipeDetailsIngredients;
    @BindView(R.id.recipe_detail_recycler_view)
    RecyclerView recipeDetailsRecyclerView;


    @BindBool(R.bool.master_detail_mode)
    boolean masterDetailMode;
    @BindString(R.string.error_default)
    String errorMessage;

    private int recipeId;
    private List<Step> mSteps;


    public static RecipeDetailFragment newInstance(int recipeId) {
        Bundle args = new Bundle();
        args.putInt(BUNDLE_RECIPE_ID, recipeId);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeId = getArguments().getInt(BUNDLE_RECIPE_ID);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mRecipeDetailAdapter.setCallback(this);
            mRecipeDetailAdapter.setHasStableIds(true);

        }
        return view;
    }


    @Override
    protected void setUp(View view) {

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipeDetailsRecyclerView.setLayoutManager(mLayoutManager);
        recipeDetailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        recipeDetailsRecyclerView.setAdapter(mRecipeDetailAdapter);

        mPresenter.loadRecipeNameFromRepo(recipeId);
        mPresenter.loadIngredientsFromRepo(recipeId);
        mPresenter.loadStepsFromRepo(recipeId);
    }

    @Override
    public void onRecipeStepClick(int stepId) {
       mPresenter.openStepDetails(recipeId, stepId);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void showIngredientsList(List<Ingredient> ingredients) {

        StringBuilder sb = new StringBuilder();

        for (Ingredient ingredient : ingredients) {

            String name = ingredient.ingredient();
            float quantity = ingredient.quantity();
            String measure = ingredient.measure();

            sb.append("\n");
            sb.append(StringUtils.formatIngredient(getContext(), name, quantity, measure));
        }

        recipeDetailsIngredients.setText(sb.toString());

    }

    @Override
    public void showStepsList(List<Step> steps) {
        mSteps = steps;
        mRecipeDetailAdapter.refreshStepList(steps);
    }


    @Override
    public void showRecipeNameInActivityTitle(String recipeName) {
        getActivity().setTitle(recipeName);
    }

    @Override
    public void showStepDetails(int stepId, Step step) {

        if (masterDetailMode) {
            RecipeStepFragment fragment =
                    RecipeStepFragment.newInstance(step);

            FragmentUtils.replaceFragmentIn(
                    getChildFragmentManager(),
                    fragment,
                    R.id.detail_fragment_container);
        } else {
            startActivity(RecipeStepActivity.getStartIntent(getContext(), stepId, mSteps));
        }

    }

    @Override
    public void showErrorMessage() {
        // User should not see this
        onError(errorMessage);
    }


}
