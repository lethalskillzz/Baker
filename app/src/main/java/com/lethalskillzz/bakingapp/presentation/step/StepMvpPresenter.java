package com.lethalskillzz.bakingapp.presentation.step;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface StepMvpPresenter <V extends StepMvpView> extends MvpPresenter<V> {

    void fetchStepData(int recipeId, int stepId);
}