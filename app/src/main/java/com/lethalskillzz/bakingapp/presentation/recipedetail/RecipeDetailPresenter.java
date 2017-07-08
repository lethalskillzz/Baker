package com.lethalskillzz.bakingapp.presentation.recipedetail;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeDetailPresenter<V extends RecipeDetailMvpView> extends BasePresenter<V>
        implements RecipeDetailMvpPresenter<V> {

    private static final String TAG = "RecipeDetailPresenter";

    @Inject
    public RecipeDetailPresenter(RecipeRepository recipeRepository,
                                 CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }
    

    @Override
    public void loadRecipeNameFromRepo() {
        getCompositeDisposable().add(getRecipeRepository()
                .getRecipes()
                .flatMap(Observable::fromIterable)
                .filter(recipe -> recipe.id() == recipeId)
                .subscribe(
                        // OnNext
                        recipe -> getMvpView().showRecipeNameInActivityTitle(recipe.name()),
                        // OnError
                        throwable -> getMvpView().onError()));

    }

    @Override
    public void loadIngredientsFromRepo() {

        getCompositeDisposable().add(getRecipeRepository()
                .getRecipeIngredients(recipeId)
                .subscribe(
                        // OnNext
                        getMvpView()::showIngredientsList,
                        // OnError
                        throwable -> getMvpView().showErrorMessage()));

    }

    @Override
    public void loadStepsFromRepo() {

        getCompositeDisposable().add(getRecipeRepository()
                .getRecipeSteps(recipeId)
                .subscribe(
                        // OnNext
                        getMvpView()::showStepsList,
                        // OnError
                        throwable -> getMvpView().showErrorMessage()));

    }

    @Override
    public void openStepDetails(int stepId) {
        getMvpView().showStepDetails(stepId);
    }

    @Override
    public void fetchStepData(int stepId) {

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
