package com.lethalskillzz.bakingapp.presentation.recipelist;

import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeListPresenter  <V extends RecipeListMvpView> extends BasePresenter<V>
        implements RecipeListMvpPresenter<V> {

    private static final String TAG = "RecipeListPresenter";

    @Inject
    public RecipeListPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
