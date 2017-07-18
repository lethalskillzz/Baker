package com.lethalskillzz.bakingapp.presentation.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.lethalskillzz.bakingapp.App;
import com.lethalskillzz.bakingapp.utils.AppLogger;

import javax.inject.Inject;

import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_DEFAULT_ID;
import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_RECIPE_ID;
import static com.lethalskillzz.bakingapp.utils.AppConstants.BUNDLE_RECIPE_NAME;
import static com.lethalskillzz.bakingapp.utils.AppConstants.RECIPE_WIDGET_ACTION_UPDATE;

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
        intent.setAction(RECIPE_WIDGET_ACTION_UPDATE);
        intent.putExtra(BUNDLE_RECIPE_ID, recipeId);
        intent.putExtra(BUNDLE_RECIPE_NAME, recipeName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (RECIPE_WIDGET_ACTION_UPDATE.equals(action)) {
                handleActionUpdateWidgets(intent.getIntExtra(BUNDLE_RECIPE_ID, BUNDLE_DEFAULT_ID),
                        intent.getStringExtra(BUNDLE_RECIPE_NAME));
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
