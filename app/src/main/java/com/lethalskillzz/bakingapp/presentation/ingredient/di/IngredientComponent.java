package com.lethalskillzz.bakingapp.presentation.ingredient.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@PerActivity
@Component(dependencies = IngredientComponent.class, modules =  IngredientPresenterModule.class)
public interface IngredientComponent {

    void inject(IngredientActivity activity);
}
