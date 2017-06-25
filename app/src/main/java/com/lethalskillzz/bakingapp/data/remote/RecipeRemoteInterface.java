package com.lethalskillzz.bakingapp.data.remote;

import com.lethalskillzz.bakingapp.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ibrahimabdulkadir on 23/06/2017.
 */

public interface RecipeRemoteInterface {

    Observable<List<Recipe>> getRecipes();
}
