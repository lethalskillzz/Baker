package com.lethalskillzz.baker.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.lethalskillzz.baker.di.ActivityContext;
import com.lethalskillzz.baker.di.PerActivity;
import com.lethalskillzz.baker.presentation.recipedetail.RecipeDetailAdapter;
import com.lethalskillzz.baker.presentation.recipedetail.RecipeDetailMvpPresenter;
import com.lethalskillzz.baker.presentation.recipedetail.RecipeDetailMvpView;
import com.lethalskillzz.baker.presentation.recipedetail.RecipeDetailPresenter;
import com.lethalskillzz.baker.presentation.recipelist.RecipeListAdapter;
import com.lethalskillzz.baker.presentation.recipelist.RecipeListMvpPresenter;
import com.lethalskillzz.baker.presentation.recipelist.RecipeListMvpView;
import com.lethalskillzz.baker.presentation.recipelist.RecipeListPresenter;
import com.lethalskillzz.baker.presentation.recipestep.RecipeStepMvpPresenter;
import com.lethalskillzz.baker.presentation.recipestep.RecipeStepMvpView;
import com.lethalskillzz.baker.presentation.recipestep.RecipeStepPresenter;
import com.lethalskillzz.baker.presentation.splash.SplashMvpPresenter;
import com.lethalskillzz.baker.presentation.splash.SplashMvpView;
import com.lethalskillzz.baker.presentation.splash.SplashPresenter;

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
    RecipeStepMvpPresenter<RecipeStepMvpView> provideStepPresenter(
            RecipeStepPresenter<RecipeStepMvpView> presenter) {
        return presenter;
    }


    @Provides
    RecipeListAdapter provideRecipeListAdapter() {
        return new RecipeListAdapter(new ArrayList<>(0));
    }


    @Provides
    RecipeDetailAdapter provideRecipeDetailAdapter() {
        return new RecipeDetailAdapter(new ArrayList<>(0));
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }



}
