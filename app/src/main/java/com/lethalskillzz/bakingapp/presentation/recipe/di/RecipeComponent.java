package com.lethalskillzz.bakingapp.presentation.recipe.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.recipe.RecipeActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@PerActivity
@Component(dependencies = RecipeComponent.class, modules = RecipePresenterModule.class)
public interface RecipeComponent {

    void inject(RecipeActivity activity);
}
