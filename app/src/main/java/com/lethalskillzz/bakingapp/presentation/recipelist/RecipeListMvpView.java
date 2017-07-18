package com.lethalskillzz.bakingapp.presentation.recipelist;

import com.lethalskillzz.bakingapp.data.model.Recipe;
import com.lethalskillzz.bakingapp.presentation.base.MvpView;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public interface RecipeListMvpView extends MvpView {

    void showRecipeList(List<Recipe> recipes);

    void showRecipeDetail(int recipeId);

    void refreshRecipe(boolean isRemote);
}
