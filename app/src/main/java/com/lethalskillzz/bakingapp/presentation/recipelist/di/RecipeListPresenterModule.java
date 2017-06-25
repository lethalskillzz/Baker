package com.lethalskillzz.bakingapp.presentation.recipelist.di;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListMvpView;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@Module
public class RecipeListPresenterModule {

    @Provides
    RecipeListMvpPresenter<RecipeListMvpView> provideRecipeListPresenter(
            RecipeListPresenter<RecipeListMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
