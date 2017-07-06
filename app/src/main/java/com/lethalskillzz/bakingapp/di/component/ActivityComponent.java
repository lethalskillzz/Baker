package com.lethalskillzz.bakingapp.di.component;


import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.di.module.ActivityModule;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientActivity;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailActivity;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailFragment;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListActivity;
import com.lethalskillzz.bakingapp.presentation.splash.SplashActivity;
import com.lethalskillzz.bakingapp.presentation.step.StepActivity;
import com.lethalskillzz.bakingapp.presentation.step.StepFragment;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(IngredientActivity activity);

    void inject(RecipeDetailActivity activity);

    void inject(RecipeListActivity activity);

    void inject(StepActivity activity);

    void inject(RecipeDetailFragment fragment);

    void inject(StepFragment fragment);

}
