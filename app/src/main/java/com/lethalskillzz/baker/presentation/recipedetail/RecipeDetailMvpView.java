package com.lethalskillzz.baker.presentation.recipedetail;

import com.lethalskillzz.baker.data.model.Step;
import com.lethalskillzz.baker.data.model.Ingredient;
import com.lethalskillzz.baker.presentation.base.MvpView;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public interface RecipeDetailMvpView extends MvpView {

    void showIngredientsList(List<Ingredient> ingredients);

    void showStepsList(List<Step> steps);

    void showErrorMessage();

    void showRecipeNameInActivityTitle(String recipeName);

    void showStepDetails(int stepId, Step step);


}
