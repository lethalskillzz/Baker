package com.lethalskillzz.baker.presentation.recipestep;

import com.lethalskillzz.baker.di.PerActivity;
import com.lethalskillzz.baker.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeStepMvpPresenter<V extends RecipeStepMvpView> extends MvpPresenter<V> {

    void fetchStepData(int recipeId, int stepId);
}