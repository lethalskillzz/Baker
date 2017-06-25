package com.lethalskillzz.bakingapp.presentation.step.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.step.StepActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@PerActivity
@Component(dependencies = StepComponent.class, modules = StepPresenterModule.class)
public interface StepComponent {

    void inject(StepActivity activity);
}
