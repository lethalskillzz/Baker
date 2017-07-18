package com.lethalskillzz.bakingapp.presentation.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.data.model.Ingredient;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailActivity;

import java.util.List;

/**
 * Created by ibrahimabdulkadir on 17/07/2017.
 */

public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int recipeId, String recipeName, List<Ingredient> ingredients, int appWidgetId) {

        Intent intent = RecipeDetailActivity.getStartIntent(context, recipeId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.removeAllViews(R.id.recipe_widget_ingredient_list);
        views.setTextViewText(R.id.recipe_widget_title, recipeName);
        views.setOnClickPendingIntent(R.id.recipe_widget_holder, pendingIntent);

        for(Ingredient ingredient : ingredients) {
            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(),
                    R.layout.recipe_widget_list_item);
            rvIngredient.setTextViewText(R.id.recipe_widget_ingredient_item,
                    String.valueOf(ingredient.quantity()) +
                            String.valueOf(ingredient.measure()) + " " + ingredient.ingredient());
            views.addView(R.id.recipe_widget_ingredient_list, rvIngredient);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           int recipeId, String recipeName, List<Ingredient> ingredients, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipeId, recipeName, ingredients, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {}

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {}

    @Override
    public void onEnabled(Context context) {}

    @Override
    public void onDisabled(Context context) {}
}
