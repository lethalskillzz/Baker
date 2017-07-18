package com.lethalskillzz.bakingapp.recipedetail;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailsActivityIntentTest {


  private static final String EXTRA_RECIPE_ID_KEY = "bundleStepId";
  private static final int EXTRA_RECIPE_ID_VALUE = 1;


  @Rule
  public IntentsTestRule<RecipeDetailActivity> intentsTestRule
      = new IntentsTestRule<>(RecipeDetailActivity.class);

  @Test
  public void clickOnRecyclerViewItem_runsRecipeStepActivityIntent() {

    onView(ViewMatchers.withId(R.id.recipe_detail_recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    intended(
        hasExtra(EXTRA_RECIPE_ID_KEY, EXTRA_RECIPE_ID_VALUE));
  }
}
