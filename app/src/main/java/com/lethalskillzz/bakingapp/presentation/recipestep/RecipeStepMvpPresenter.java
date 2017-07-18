package com.lethalskillzz.bakingapp.presentation.recipestep;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeStepMvpPresenter<V extends RecipeStepMvpView> extends MvpPresenter<V> {

    void fetchStepData(int recipeId, int stepId);
}