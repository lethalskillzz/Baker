package com.lethalskillzz.bakingapp.di.component;

import android.app.Application;
import android.content.Context;

import com.lethalskillzz.bakingapp.MvpApp;
import com.lethalskillzz.bakingapp.data.DataManager;
import com.lethalskillzz.bakingapp.di.ApplicationContext;
import com.lethalskillzz.bakingapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}