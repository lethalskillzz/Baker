package com.lethalskillzz.bakingapp.presentation.ingredient;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class IngredientPresenter <V extends IngredientMvpView> extends BasePresenter<V>
        implements IngredientMvpPresenter<V> {

    private static final String TAG = "IngredientPresenter";

    @Inject
    public IngredientPresenter(RecipeRepository recipeRepository,
                          CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }
}
