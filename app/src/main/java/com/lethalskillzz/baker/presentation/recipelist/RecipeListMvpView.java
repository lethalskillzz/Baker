package com.lethalskillzz.baker.presentation.recipelist;

import com.lethalskillzz.baker.data.model.Recipe;
import com.lethalskillzz.baker.presentation.base.MvpView;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public interface RecipeListMvpView extends MvpView {

    void showRecipeList(List<Recipe> recipes);

    void showRecipeDetail(int recipeId);

    void refreshRecipe(boolean isRemote);
}
