package com.lethalskillzz.bakingapp.di.component;


import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);



}
