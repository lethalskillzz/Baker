package com.lethalskillzz.baker.presentation.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.lethalskillzz.baker.App;
import com.lethalskillzz.baker.utils.AppConstants;
import com.lethalskillzz.baker.utils.AppLogger;

import javax.inject.Inject;

/**
 * Created by ibrahimabdulkadir on 17/07/2017.
 */

public class RecipeWidgetService extends IntentService {

    @Inject
    RecipeWidgetHelper recipeWidgetHelper;


    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

         DaggerRecipeWidgetHelperComponent.builder()
                .applicationComponent(((App) getApplication()).getComponent())
                .build().inject(this);
    }


    public static void startActionUpdateRecipeWidgets(Context context, int recipeId, String recipeName) {
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.setAction(AppConstants.RECIPE_WIDGET_ACTION_UPDATE);
        intent.putExtra(AppConstants.BUNDLE_RECIPE_ID, recipeId);
        intent.putExtra(AppConstants.BUNDLE_RECIPE_NAME, recipeName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (AppConstants.RECIPE_WIDGET_ACTION_UPDATE.equals(action)) {
                handleActionUpdateWidgets(intent.getIntExtra(AppConstants.BUNDLE_RECIPE_ID, AppConstants.BUNDLE_DEFAULT_ID),
                        intent.getStringExtra(AppConstants.BUNDLE_RECIPE_NAME));
            }
        }
    }

    private void handleActionUpdateWidgets(int recipeId, String recipeName) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));

        recipeWidgetHelper
                .loadIngredientsFromRepo(recipeId).subscribe(
                // OnNext
                ingredients ->
                        RecipeWidgetProvider.updateRecipeWidgets(this, appWidgetManager, recipeId,
                                recipeName, ingredients, appWidgetIds),
                // OnError
                throwable -> AppLogger.d("Error: unable to populate widget data."));

    }
}
