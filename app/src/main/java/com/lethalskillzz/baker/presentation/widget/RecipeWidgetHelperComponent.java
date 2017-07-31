package com.lethalskillzz.baker.presentation.widget;


import com.lethalskillzz.baker.di.PerActivity;
import com.lethalskillzz.baker.di.component.ApplicationComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
interface RecipeWidgetHelperComponent {

  void inject(RecipeWidgetService recipeWidgetService);
}
