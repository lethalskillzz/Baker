package com.lethalskillzz.bakingapp.presentation.step.di;

import com.lethalskillzz.bakingapp.presentation.step.StepMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.step.StepMvpView;
import com.lethalskillzz.bakingapp.presentation.step.StepPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@Module
public class StepPresenterModule {

    @Provides
    StepMvpPresenter<StepMvpView> provideStepPresenter(
            StepPresenter<StepMvpView> presenter) {
        return presenter;
    }

}
