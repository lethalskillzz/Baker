package com.lethalskillzz.bakingapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.di.component.ApplicationComponent;
import com.lethalskillzz.bakingapp.di.component.DaggerApplicationComponent;
import com.lethalskillzz.bakingapp.di.module.ApplicationModule;
import com.lethalskillzz.bakingapp.utils.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

public class App extends Application {

    @Inject
    RecipeRepository mRecipeRepository;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            AppLogger.init();
            Stetho.initializeWithDefaults(this);
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
