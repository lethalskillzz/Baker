package com.lethalskillzz.bakingapp.di.component;


import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.di.module.ActivityModule;
import com.lethalskillzz.bakingapp.ui.ingredient.IngredientActivity;
import com.lethalskillzz.bakingapp.ui.recipe.RecipeActivity;
import com.lethalskillzz.bakingapp.ui.recipelist.RecipeListActivity;
import com.lethalskillzz.bakingapp.ui.splash.SplashActivity;
import com.lethalskillzz.bakingapp.ui.step.StepActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(IngredientActivity activity);

    void inject(RecipeActivity activity);

    void inject(RecipeListActivity activity);

    void inject(StepActivity activity);

}
