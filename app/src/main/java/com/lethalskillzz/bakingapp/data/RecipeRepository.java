package com.lethalskillzz.bakingapp.data;


import android.content.Context;

import com.lethalskillzz.bakingapp.data.local.prefs.PreferencesHelper;
import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.data.model.Recipe;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.di.ApplicationContext;
import com.lethalskillzz.bakingapp.di.Local;
import com.lethalskillzz.bakingapp.di.Remote;
import com.lethalskillzz.bakingapp.utils.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by ibrahimabdulkadir on 20/06/2017.
 */

@Singleton
public class RecipeRepository implements RecipeDataSource {


  private final Context context;
  private final RecipeDataSource recipeRemoteDataSource;
  private final RecipeDataSource recipeLocalDataSource;
  private final PreferencesHelper preferencesHelper;


  @Inject
  public RecipeRepository(
          @ApplicationContext Context context,
          @Remote RecipeDataSource recipeRemoteDataSource,
          @Local RecipeDataSource recipeLocalDataSource,
          PreferencesHelper preferencesHelper) {

    this.context = context;
    this.recipeRemoteDataSource = recipeRemoteDataSource;
    this.recipeLocalDataSource = recipeLocalDataSource;
    this.preferencesHelper = preferencesHelper;
  }

  @Override
  public Observable<List<Recipe>> getRecipes() {

    if (!preferencesHelper.isRecipeListSynced()) {
      return recipeRemoteDataSource
              .getRecipes()
              .compose(RxUtils.applySchedulers())
              .doOnNext(recipeList -> {
                recipeLocalDataSource.saveRecipes(recipeList);
                preferencesHelper.saveRecipeNamesList(recipeList);
              });
    } else {
      return recipeLocalDataSource
              .getRecipes()
              .compose(RxUtils.applySchedulers());
    }
  }

  @Override
  public Observable<List<Ingredient>> getRecipeIngredients(int recipeId) {
    return recipeLocalDataSource
            .getRecipeIngredients(recipeId)
            .compose(RxUtils.applySchedulers());
  }

  @Override
  public Observable<List<Ingredient>> getRecipeIngredients(String recipeName) {
    return recipeLocalDataSource
            .getRecipeIngredients(recipeName)
            .compose(RxUtils.applySchedulers());
  }

  @Override
  public Observable<List<Step>> getRecipeSteps(int recipeId) {
    return recipeLocalDataSource
            .getRecipeSteps(recipeId)
            .compose(RxUtils.applySchedulers());
  }

  @Override
  public void saveRecipes(List<Recipe> recipes) {
    recipeLocalDataSource.saveRecipes(recipes);
  }

  public void markRepoAsSynced(boolean synced) {
    preferencesHelper.setRecipeListSynced(synced);
  }

  public PreferencesHelper getPreferencesHelper() {
    return preferencesHelper;
  }
}
