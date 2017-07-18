package com.lethalskillzz.bakingapp.presentation.recipestep;

import com.lethalskillzz.bakingapp.presentation.base.MvpView;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public interface RecipeStepMvpView extends MvpView {

    void showErrorMessage();

    void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl);
}
