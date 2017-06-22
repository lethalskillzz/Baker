package com.lethalskillzz.bakingapp.ui.step;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.ui.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface StepMvpPresenter <V extends StepMvpView> extends MvpPresenter<V> {

}