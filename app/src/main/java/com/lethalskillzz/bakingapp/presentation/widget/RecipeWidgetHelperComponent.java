package com.lethalskillzz.bakingapp.presentation.widget;


import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.di.component.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
interface RecipeWidgetHelperComponent {

  void inject(RecipeWidgetService recipeWidgetService);
}
