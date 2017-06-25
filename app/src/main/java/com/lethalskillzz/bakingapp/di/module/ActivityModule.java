package com.lethalskillzz.bakingapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.lethalskillzz.bakingapp.di.ActivityContext;
import com.lethalskillzz.bakingapp.utils.rx.AppSchedulerProvider;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
