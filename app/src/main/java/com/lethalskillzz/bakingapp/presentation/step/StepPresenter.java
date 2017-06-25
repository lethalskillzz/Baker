package com.lethalskillzz.bakingapp.presentation.step;

import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class StepPresenter  <V extends StepMvpView> extends BasePresenter<V>
        implements StepMvpPresenter<V> {

    private static final String TAG = "StepPresenter";

    @Inject
    public StepPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
