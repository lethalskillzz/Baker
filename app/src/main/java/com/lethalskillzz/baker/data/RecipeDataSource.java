package com.lethalskillzz.baker.data;


import com.lethalskillzz.baker.data.model.Step;
import com.lethalskillzz.baker.data.model.Ingredient;
import com.lethalskillzz.baker.data.model.Recipe;

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
