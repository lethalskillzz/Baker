package com.lethalskillzz.bakingapp.presentation.recipelist.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@PerActivity
@Component(dependencies = RecipeListComponent.class, modules = RecipeListPresenterModule.class)
public interface RecipeListComponent {

    void inject(RecipeListActivity activity);
}
