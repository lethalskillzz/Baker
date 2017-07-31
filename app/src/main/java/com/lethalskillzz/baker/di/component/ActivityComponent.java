package com.lethalskillzz.baker.di.component;


import com.lethalskillzz.baker.di.module.ActivityModule;
import com.lethalskillzz.baker.presentation.recipelist.RecipeListActivity;
import com.lethalskillzz.baker.presentation.recipestep.RecipeStepFragment;
import com.lethalskillzz.baker.di.PerActivity;
import com.lethalskillzz.baker.presentation.recipedetail.RecipeDetailActivity;
import com.lethalskillzz.baker.presentation.recipedetail.RecipeDetailFragment;
import com.lethalskillzz.baker.presentation.recipestep.RecipeStepActivity;
import com.lethalskillzz.baker.presentation.splash.SplashActivity;

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
