package com.lethalskillzz.baker.presentation.recipelist;

import com.lethalskillzz.baker.di.PerActivity;
import com.lethalskillzz.baker.presentation.base.MvpPresenter;
import com.lethalskillzz.baker.data.idlingresource.RecipesIdlingResource;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeListMvpPresenter <V extends RecipeListMvpView> extends MvpPresenter<V> {

    void loadRecipes(boolean forcedSync, RecipesIdlingResource resource);

    void openRecipeDetails(int recipeId);

}