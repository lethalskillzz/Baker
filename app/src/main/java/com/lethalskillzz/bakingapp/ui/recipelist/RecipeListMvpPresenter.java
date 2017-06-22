package com.lethalskillzz.bakingapp.ui.recipelist;

import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.ui.base.MvpPresenter;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

@PerActivity
public interface RecipeListMvpPresenter <V extends RecipeListMvpView> extends MvpPresenter<V> {

}