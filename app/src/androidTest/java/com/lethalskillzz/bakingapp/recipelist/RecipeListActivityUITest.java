package com.lethalskillzz.bakingapp.recipelist;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.recipelist.RecipeListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityUITest {

  @Rule
  public ActivityTestRule<RecipeListActivity> activityTestRule =
      new ActivityTestRule<>(RecipeListActivity.class);

  private IdlingResource idlingResource;

  @Before
  public void registerIdlingResource() {
    idlingResource = activityTestRule.getActivity().getIdlingResource();
    Espresso.registerIdlingResources(idlingResource);
  }


  @Test
  public void clickOnRecyclerViewItem_opensRecipeDetailsActivity() {

    onView(withId(R.id.recipe_list_recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    onView(withId(R.id.recipe_details_ingredients))
        .check(matches(isDisplayed()));
  }

  @After
  public void unregisterIdlingResource() {
    if (idlingResource != null) {
      Espresso.unregisterIdlingResources(idlingResource);
    }
  }
}
