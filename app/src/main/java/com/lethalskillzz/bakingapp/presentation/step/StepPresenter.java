package com.lethalskillzz.bakingapp.presentation.step;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class StepPresenter  <V extends StepMvpView> extends BasePresenter<V>
        implements StepMvpPresenter<V> {

    private static final String TAG = "StepPresenter";

    @Inject
    public StepPresenter(RecipeRepository recipeRepository,
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
