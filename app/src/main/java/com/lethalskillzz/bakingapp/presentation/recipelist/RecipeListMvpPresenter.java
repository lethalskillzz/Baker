package com.lethalskillzz.bakingapp.presentation.recipelist;

import com.lethalskillzz.bakingapp.data.idlingresource.RecipesIdlingResource;
import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeListMvpPresenter <V extends RecipeListMvpView> extends MvpPresenter<V> {

    void loadRecipes(boolean forcedSync, RecipesIdlingResource resource);

    void openRecipeDetails(int recipeId);
}