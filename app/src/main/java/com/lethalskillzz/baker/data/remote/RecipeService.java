package com.lethalskillzz.baker.data.remote;

import com.lethalskillzz.baker.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ibrahimabdulkadir on 23/06/2017.
 */

public interface RecipeService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> loadRecipesFromServer();
}
