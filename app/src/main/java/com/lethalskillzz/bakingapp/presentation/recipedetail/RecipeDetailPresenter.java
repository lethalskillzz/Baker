package com.lethalskillzz.bakingapp.presentation.recipedetail;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeDetailPresenter<V extends RecipeDetailMvpView> extends BasePresenter<V>
        implements RecipeDetailMvpPresenter<V> {

    private static final String TAG = "RecipeDetailPresenter";

    @Inject
    public RecipeDetailPresenter(RecipeRepository recipeRepository,
                                 CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }
}
