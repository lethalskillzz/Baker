package com.lethalskillzz.baker.presentation.recipestep;

import com.lethalskillzz.baker.data.RecipeRepository;
import com.lethalskillzz.baker.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeStepPresenter<V extends RecipeStepMvpView> extends BasePresenter<V>
        implements RecipeStepMvpPresenter<V> {

    private static final String TAG = "RecipeStepPresenter";

    @Inject
    public RecipeStepPresenter(RecipeRepository recipeRepository,
                               CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }



    @Override
    public void fetchStepData(int recipeId, int stepId) {

        getCompositeDisposable().add(getRecipeRepository()
                .getRecipeSteps(recipeId)
                .flatMap(Observable::fromIterable)
                .filter(step -> step.id() == stepId)
                .subscribe(
                        // OnNext
                        step ->
                                getMvpView().refreshStepContainerFragment(
                                        step.description(),
                                        step.videoURL(),
                                        step.thumbnailURL()),
                        // OnError
                        throwable -> getMvpView().showErrorMessage()));
    }

}
