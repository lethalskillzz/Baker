package com.lethalskillzz.baker.di.component;

import android.app.Application;
import android.content.Context;

import com.lethalskillzz.baker.App;
import com.lethalskillzz.baker.data.RecipeRepository;
import com.lethalskillzz.baker.di.ApplicationContext;
import com.lethalskillzz.baker.di.module.ApplicationModule;

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