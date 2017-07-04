package com.lethalskillzz.bakingapp.presentation.recipelist;

import com.lethalskillzz.bakingapp.data.RecipeRepository;
import com.lethalskillzz.bakingapp.data.idlingresource.RecipesIdlingResource;
import com.lethalskillzz.bakingapp.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by ibrahimabdulkadir on 21/06/2017.
 */

public class RecipeListPresenter  <V extends RecipeListMvpView> extends BasePresenter<V>
        implements RecipeListMvpPresenter<V> {

    private static final String TAG = "RecipeListPresenter";

    @Inject
    public RecipeListPresenter(RecipeRepository recipeRepository,
                               CompositeDisposable compositeDisposable) {
        super(recipeRepository, compositeDisposable);
    }

    @Override
    public void loadRecipes(RecipesIdlingResource resource) {

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
                            getMvpView().showRecipes(recipeList);
                            if (resource != null) resource.setIdleState(true);
                            //if (forcedSync) recipesView.showCompletedMessage();
                        },
                        // OnError
                        throwable -> {
                            getMvpView().hideLoading();

                            if (throwable instanceof HttpException) {
                                HttpException e = (HttpException) throwable;
                                handleApiError(e);
                            }

                            getRecipeRepository().markRepoAsSynced(false);
                        }));
    }


    @Override
    public void openRecipeDetails(int recipeId) {
        getMvpView().showRecipeDetails(recipeId);
    }

}