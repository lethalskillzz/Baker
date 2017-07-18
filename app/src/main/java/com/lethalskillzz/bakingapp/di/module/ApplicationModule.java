package com.lethalskillzz.bakingapp.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lethalskillzz.bakingapp.BuildConfig;
import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.RecipeDataSource;
import com.lethalskillzz.bakingapp.data.local.RecipeLocalDataSource;
import com.lethalskillzz.bakingapp.data.local.db.DbHelper;
import com.lethalskillzz.bakingapp.data.local.prefs.PreferencesHelper;
import com.lethalskillzz.bakingapp.data.remote.RecipeRemoteDataSource;
import com.lethalskillzz.bakingapp.data.remote.RecipeService;
import com.lethalskillzz.bakingapp.data.remote.ServiceFactory;
import com.lethalskillzz.bakingapp.di.ApplicationContext;
import com.lethalskillzz.bakingapp.di.DatabaseInfo;
import com.lethalskillzz.bakingapp.di.Local;
import com.lethalskillzz.bakingapp.di.Remote;
import com.lethalskillzz.bakingapp.utils.AppConstants;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ibrahimabdulkadir on 19/06/2017.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    RecipeService provideRecipeService() {
        return ServiceFactory.createFrom(RecipeService.class, BuildConfig.BASE_URL);
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Singleton
    @Provides
    @Local
    RecipeDataSource provideRecipeLocalDataSource(BriteDatabase briteDatabase) {
        return new RecipeLocalDataSource(briteDatabase);
    }

    @Singleton
    @Provides
    @Remote
    RecipeDataSource provideRecipeRemoteDataSource(RecipeService service) {
        return new RecipeRemoteDataSource(service);
    }

    @Singleton
    @Provides
    @NonNull
    BriteDatabase provideBriteDatabase(SqlBrite sqlBrite, DbHelper dbHelper, Scheduler scheduler) {
        return sqlBrite.wrapDatabaseHelper(dbHelper, scheduler);
    }

    @Singleton
    @Provides
    @NonNull
    SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder().build();
    }

    @Singleton
    @Provides
    @NonNull
    DbHelper provideDbHelper(@ApplicationContext Context context) {
        return new DbHelper(context);
    }

    @Provides
    @NonNull
    Scheduler provideScheduler() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @NonNull
    PreferencesHelper providePreferencesHelper(@ApplicationContext Context context) {
        return new PreferencesHelper(context);
    }

}
