package com.lethalskillzz.bakingapp.presentation.recipedetail;

import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.presentation.base.MvpView;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public interface RecipeDetailMvpView extends MvpView {

    void showIngredientsList(List<Ingredient> ingredients);

    void showStepsList(List<Step> steps);

    void showErrorMessage();

    void showRecipeNameInActivityTitle(String recipeName);

    void showStepDetails(int stepId);

    void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl);
}
