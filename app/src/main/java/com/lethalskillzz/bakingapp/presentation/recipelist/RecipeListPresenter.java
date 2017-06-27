package com.lethalskillzz.bakingapp.presentation.recipelist;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeListPresenter  <V extends RecipeListMvpView> extends BasePresenter<V>
        implements RecipeListMvpPresenter<V> {

    private static final String TAG = "RecipeListPresenter";

    @Inject
    public RecipeListPresenter(RecipeRepository recipeRepository,
                               CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }
}
