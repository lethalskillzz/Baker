package com.lethalskillzz.bakingapp.presentation.recipe;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeMvpPresenter <V extends RecipeMvpView> extends MvpPresenter<V> {

}

