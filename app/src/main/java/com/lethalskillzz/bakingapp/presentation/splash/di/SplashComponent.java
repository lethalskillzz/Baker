package com.lethalskillzz.bakingapp.presentation.splash.di;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.splash.SplashActivity;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 25/06/2017.
 */

@PerActivity
@Component(dependencies = SplashComponent.class, modules = SplashPresenterModule.class)
public interface SplashComponent {

    void inject(SplashActivity activity);

}
