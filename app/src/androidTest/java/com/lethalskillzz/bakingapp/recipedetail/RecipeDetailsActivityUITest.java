package com.lethalskillzz.bakingapp.recipedetail;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lethalskillzz.bakingapp.R;
import com.lethalskillzz.bakingapp.presentation.recipedetail.RecipeDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailsActivityUITest {

  private static final int STEP_WITH_VIDEO = 0;
  private static final int STEP_WITHOUT_VIDEO = 1;

  @Rule
  public ActivityTestRule<RecipeDetailActivity> activityActivityTestRule =
      new ActivityTestRule<>(RecipeDetailActivity.class);

  @Test
  public void clickOnRecyclerViewItem_opensRecipeStepActivity() {

    onView(withId(R.id.recipe_detail_recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    onView(withId(R.id.step_viewpager))
        .check(matches(isDisplayed()));
  }

  @Test
  public void clickOnStepWithVideo_showsVideoPlayerView() {

    onView(withId(R.id.recipe_detail_recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(STEP_WITH_VIDEO, click()));

    onView(
        allOf(
            withId(R.id.fragment_step_exo_player_view),
            withParent(withParent(withId(R.id.step_viewpager))),
            isDisplayed()))
        .check(matches(isDisplayed()));
  }

  @Test
  public void clickOnStepWithoutVideo_hidesVideoPlayerView() {

    onView(withId(R.id.recipe_detail_recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(STEP_WITHOUT_VIDEO, click()));

    onView(
        allOf(
            withId(R.id.fragment_step_exo_player_view),
            withParent(withParent(withId(R.id.step_viewpager))),
            isDisplayed()))
        .check(doesNotExist());
  }
}
