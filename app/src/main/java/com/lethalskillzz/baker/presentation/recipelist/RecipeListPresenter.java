package com.lethalskillzz.baker.presentation.recipelist;

import com.lethalskillzz.baker.data.RecipeRepository;
import com.lethalskillzz.baker.presentation.base.BasePresenter;
import com.lethalskillzz.baker.data.idlingresource.RecipesIdlingResource;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeListPresenter  <V extends RecipeListMvpView> extends BasePresenter<V>
        implements RecipeListMvpPresenter<V> {

    @Inject
    public RecipeListPresenter(RecipeRepository recipeRepository,
                               CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }

    @Override
    public void loadRecipes(boolean forcedSync, RecipesIdlingResource resource) {

        if (forcedSync) {
            getRecipeRepository().markRepoAsSynced(false);
        }

        getCompositeDisposable().add(getRecipeRepository()
                .getRecipes()
                .doOnSubscribe(disposable -> {
                    getMvpView().showLoading();
                    if (resource != null) resource.setIdleState(false);
                })
                .subscribe(
                        //OnNext
                        recipeList -> {
                            getRecipeRepository().markRepoAsSynced(true);
                            getMvpView().hideLoading();
                            getMvpView().showRecipeList(recipeList);
                            if (resource != null) resource.setIdleState(true);
                        },
                        // OnError
                        throwable -> {
                            getMvpView().hideLoading();
                            getMvpView().refreshRecipe(false);

                            if (throwable instanceof HttpException) {
                                HttpException e = (HttpException) throwable;
                                handleApiError(e);
                            }

                            getRecipeRepository().markRepoAsSynced(false);
                        }));
    }


    @Override
    public void openRecipeDetails(int recipeId) {
        getMvpView().showRecipeDetail(recipeId);
    }

}