package com.lethalskillzz.bakingapp.presentation.recipe;

import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipePresenter  <V extends RecipeMvpView> extends BasePresenter<V>
        implements RecipeMvpPresenter<V> {

    private static final String TAG = "RecipePresenter";

    @Inject
    public RecipePresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
