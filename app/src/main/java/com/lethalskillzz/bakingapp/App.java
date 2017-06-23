package com.lethalskillzz.bakingapp;

import android.app.Application;


import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.di.component.ApplicationComponent;
import com.lethalskillzz.bakingapp.di.component.DaggerApplicationComponent;
import com.lethalskillzz.bakingapp.di.module.ApplicationModule;
import com.lethalskillzz.bakingapp.utils.AppLogger;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

public class App extends Application {

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
