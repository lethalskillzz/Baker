package com.lethalskillzz.baker.presentation.recipestep;

import com.lethalskillzz.baker.presentation.base.MvpView;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public interface RecipeStepMvpView extends MvpView {

    void showErrorMessage();

    void refreshStepContainerFragment(String desc, String videoUrl, String imageUrl);
}
