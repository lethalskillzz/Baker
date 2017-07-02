package com.lethalskillzz.bakingapp.data.remote;

import com.lethalskillzz.bakingapp.data.RecipeDataSource;
import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.data.model.Recipe;
import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.utils.AppLogger;
import com.lethalskillzz.bakingapp.utils.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ibrahimabdulkadir on 23/06/2017.
 */

public class RecipeRemoteDataSource  implements RecipeDataSource {

    private final RecipeService service;

    @Inject
    public RecipeRemoteDataSource(RecipeService service) {
        this.service = service;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return service
                .loadRecipesFromServer()
                .compose(RxUtils.applySchedulers())
                .doOnSubscribe(disposable -> AppLogger.d("Sync started..."))
                .doOnError(throwable ->  AppLogger.d("Sync failed!"))
                .doOnComplete(() -> AppLogger.d("Sync completed."));
    }

    @Override
    public Observable<List<Ingredient>> getRecipeIngredients(int recipeId) {
        throw new UnsupportedOperationException("getRecipeIngredients in RemoteDataSource is not implemented!");
    }

    @Override
    public Observable<List<Ingredient>> getRecipeIngredients(String recipeName) {
        throw new UnsupportedOperationException("getRecipeIngredients in RemoteDataSource is not implemented!");
    }

    @Override
    public Observable<List<Step>> getRecipeSteps(int recipeId) {
        throw new UnsupportedOperationException("getRecipeSteps in RemoteDataSource is not implemented!");
    }

    @Override
    public void saveRecipes(List<Recipe> recipes) {
        throw new UnsupportedOperationException("saveRecipes in RemoteDataSource is not implemented!");
    }


}
