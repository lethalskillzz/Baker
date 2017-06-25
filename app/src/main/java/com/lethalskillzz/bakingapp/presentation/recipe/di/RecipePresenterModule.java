package com.lethalskillzz.bakingapp.presentation.recipe.di;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.recipe.RecipeMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.recipe.RecipeMvpView;
import com.lethalskillzz.bakingapp.presentation.recipe.RecipePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@Module
public class RecipePresenterModule {

    @Provides
    @PerActivity
    RecipeMvpPresenter<RecipeMvpView> provideRecipePresenter(
            RecipePresenter<RecipeMvpView> presenter) {
        return presenter;
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
