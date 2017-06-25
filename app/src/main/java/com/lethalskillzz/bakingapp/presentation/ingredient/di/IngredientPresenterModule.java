package com.lethalskillzz.bakingapp.presentation.ingredient.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientMvpView;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@Module
public class IngredientPresenterModule {

    @Provides
    @PerActivity
    IngredientMvpPresenter<IngredientMvpView> provideIngredientPresenter(
            IngredientPresenter<IngredientMvpView> presenter) {
        return presenter;
    }
}
