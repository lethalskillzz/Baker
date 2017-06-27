package com.lethalskillzz.bakingapp.di.component;

import android.app.Application;
import android.content.Context;

import com.lethalskillzz.bakingapp.App;
import com.lethalskillzz.bakingapp.data.RecipeRepository;
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

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    RecipeRepository getRecipeRepository();
}