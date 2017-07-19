package com.lethalskillzz.bakingapp.di.component;


import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.di.module.ActivityModule;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailActivity;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailFragment;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListActivity;
import com.lethalskillzz.bakingapp.presentation.recipestep.RecipeStepActivity;
import com.lethalskillzz.bakingapp.presentation.recipestep.RecipeStepFragment;
import com.lethalskillzz.bakingapp.presentation.splash.SplashActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(RecipeDetailActivity activity);

    void inject(RecipeListActivity activity);

    void inject(RecipeStepActivity activity);

    void inject(RecipeDetailFragment fragment);

    void inject(RecipeStepFragment fragment);

}
