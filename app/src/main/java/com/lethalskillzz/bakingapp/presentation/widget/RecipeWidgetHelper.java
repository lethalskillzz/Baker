package com.lethalskillzz.bakingapp.presentation.widget;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.data.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ibrahimabdulkadir on 17/07/2017.
 */

public class RecipeWidgetHelper {

    private final RecipeRepository mRecipeRepository;

    @Inject
    public RecipeWidgetHelper(RecipeRepository recipeRepository) {
        mRecipeRepository = recipeRepository;
    }


    Observable<Recipe> loadRecipeFromRepo(int recipeId) {
        return mRecipeRepository.getRecipes()
                .flatMap(Observable::fromIterable)
                .filter(recipe -> recipe.id() == recipeId);
    }


    Observable<List<Ingredient>> loadIngredientsFromRepo(int recipeId) {

        return mRecipeRepository
                .getRecipeIngredients(recipeId);
    }


}
