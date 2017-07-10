package com.lethalskillzz.bakingapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.lethalskillzz.bakingapp.data.model.Step;
import com.lethalskillzz.bakingapp.di.ActivityContext;
import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientMvpView;
import com.lethalskillzz.bakingapp.presentation.ingredient.IngredientPresenter;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailAdapter;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailMvpView;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailPresenter;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListMvpView;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListPresenter;
import com.lethalskillzz.bakingapp.presentation.splash.SplashMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.splash.SplashMvpView;
import com.lethalskillzz.bakingapp.presentation.splash.SplashPresenter;
import com.lethalskillzz.bakingapp.presentation.step.StepMvpPresenter;
import com.lethalskillzz.bakingapp.presentation.step.StepMvpView;
import com.lethalskillzz.bakingapp.presentation.step.StepPresenter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IngredientMvpPresenter<IngredientMvpView> provideIngredientPresenter(
            IngredientPresenter<IngredientMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    RecipeListMvpPresenter<RecipeListMvpView> provideRecipeListPresenter(
            RecipeListPresenter<RecipeListMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    RecipeDetailMvpPresenter<RecipeDetailMvpView> provideRecipeDetailPresenter(
            RecipeDetailPresenter<RecipeDetailMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    StepMvpPresenter<StepMvpView> provideStepPresenter(
            StepPresenter<StepMvpView> presenter) {
        return presenter;
    }


    @Provides
    RecipeDetailAdapter provideRecipeDetailAdapter() {
        return new RecipeDetailAdapter(new ArrayList<Step>());
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


}
