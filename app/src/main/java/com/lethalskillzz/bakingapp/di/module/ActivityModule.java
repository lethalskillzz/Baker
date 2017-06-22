package com.lethalskillzz.bakingapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.lethalskillzz.bakingapp.di.ActivityContext;
import com.lethalskillzz.bakingapp.di.PerActivity;
import com.lethalskillzz.bakingapp.ui.ingredient.IngredientMvpPresenter;
import com.lethalskillzz.bakingapp.ui.ingredient.IngredientMvpView;
import com.lethalskillzz.bakingapp.ui.ingredient.IngredientPresenter;
import com.lethalskillzz.bakingapp.ui.recipe.RecipeMvpPresenter;
import com.lethalskillzz.bakingapp.ui.recipe.RecipeMvpView;
import com.lethalskillzz.bakingapp.ui.recipe.RecipePresenter;
import com.lethalskillzz.bakingapp.ui.recipelist.RecipeListMvpPresenter;
import com.lethalskillzz.bakingapp.ui.recipelist.RecipeListMvpView;
import com.lethalskillzz.bakingapp.ui.recipelist.RecipeListPresenter;
import com.lethalskillzz.bakingapp.ui.splash.SplashMvpPresenter;
import com.lethalskillzz.bakingapp.ui.splash.SplashMvpView;
import com.lethalskillzz.bakingapp.ui.splash.SplashPresenter;
import com.lethalskillzz.bakingapp.ui.step.StepMvpPresenter;
import com.lethalskillzz.bakingapp.ui.step.StepMvpView;
import com.lethalskillzz.bakingapp.ui.step.StepPresenter;
import com.lethalskillzz.bakingapp.utils.rx.AppSchedulerProvider;
import com.lethalskillzz.bakingapp.utils.rx.SchedulerProvider;

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
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
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
    RecipeMvpPresenter<RecipeMvpView> provideRecipePresenter(
            RecipePresenter<RecipeMvpView> presenter) {
        return presenter;
    }

    @Provides
    RecipeListMvpPresenter<RecipeListMvpView> provideRecipeListPresenter(
            RecipeListPresenter<RecipeListMvpView> presenter) {
        return presenter;
    }

    @Provides
    StepMvpPresenter<StepMvpView> provideStepPresenter(
            StepPresenter<StepMvpView> presenter) {
        return presenter;
    }


//    @Provides
//    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
//        return new FeedPagerAdapter(activity.getSupportFragmentManager());
//    }
//
//    @Provides
//    OpenSourceAdapter provideOpenSourceAdapter() {
//        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
//    }
//
//    @Provides
//    BlogAdapter provideBlogAdapter() {
//        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
//    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
