package com.lethalskillzz.bakingapp.presentation.splash.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.splash.SplashMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.splash.SplashMvpView;
import com.lethalskillzz.bakingapp.presentation.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@Module
public class SplashPresenterModule {

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }
}
