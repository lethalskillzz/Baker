package com.lethalskillzz.baker.presentation.recipedetail;

import com.lethalskillzz.baker.di.PerActivity;
import com.lethalskillzz.baker.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeDetailMvpPresenter<V extends RecipeDetailMvpView> extends MvpPresenter<V> {

    void loadRecipeNameFromRepo(int recipeId);

    void loadIngredientsFromRepo(int recipeId);

    void loadStepsFromRepo(int recipeId);

    void openStepDetails(int recipeId, int stepId);


}

