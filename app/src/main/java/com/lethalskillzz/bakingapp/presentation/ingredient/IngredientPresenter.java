package com.lethalskillzz.bakingapp.presentation.ingredient;

import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class IngredientPresenter <V extends IngredientMvpView> extends BasePresenter<V>
        implements IngredientMvpPresenter<V> {

    private static final String TAG = "IngredientPresenter";

    @Inject
    public IngredientPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
