package com.lethalskillzz.bakingapp.ui.splash;

import android.os.Handler;

import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.ui.base.BasePresenter;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    private static final String TAG = "SplashPresenter";

    // Splash screen timeout
    private static int SPLASH_TIME_OUT = 3000;

    @Inject
    public SplashPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);


            new Handler().postDelayed(new Runnable() {

                //Showing splash screen with a timer
                @Override
                public void run() {
                    getMvpView().openRecipeActivity();

                }

            }, SPLASH_TIME_OUT);


    }


}
