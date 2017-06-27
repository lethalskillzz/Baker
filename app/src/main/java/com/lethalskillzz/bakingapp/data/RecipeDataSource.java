package com.lethalskillzz.bakingapp.data;


import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.data.model.Recipe;
import com.lethalskillzz.bakingapp.data.model.Step;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ibrahimabdulkadir on 24/06/2017.
 */

public interface RecipeDataSource {

  Observable<List<Recipe>> getRecipes();

  Observable<List<Ingredient>> getRecipeIngredients(int recipeId);

  Observable<List<Ingredient>> getRecipeIngredients(String recipeName);

  Observable<List<Step>> getRecipeSteps(int recipeId);

  void saveRecipes(List<Recipe> recipes);
}
