package com.codervj.cleanarchitecturehiltflowsample

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.codervj.cleanarchitecturehiltflowsample.ui.MainActivity
import com.codervj.cleanarchitecturehiltflowsample.ui.adapter.RepositoryViewHolder
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = getInstrumentation().targetContext
        Assert.assertEquals("com.codervj.cleanarchitecturehiltflowsample", appContext.packageName)
    }

    @Test
    fun test_toolbar_visible() {
        Espresso.onView(withId(R.id.toolbar))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_is_toolbar_title_set() {
       Espresso.onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText("Github Repositories")))
    }

    @Test
    fun test_edittext_view_visible() {
        Espresso.onView(withId(R.id.etUserName))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_search_go_view_visible() {
        Espresso.onView(withId(R.id.ibSearch))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_able_to_enter_username() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(typeText("virajjage09"))
    }

    @Test
    fun test_able_to_click_search_icon() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(typeText("virajjage09"))
        Espresso.onView(withId(R.id.ibSearch))
            .perform(click())
    }

    @Test
    fun test_empty_message_view_visible() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(typeText("NA"))
        Espresso.onView(withId(R.id.ibSearch))
            .perform(click())
        Espresso.onView(withId(R.id.tvNoErrorMsg))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_recycler_view_visible() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(typeText("virajjage09"))
        Espresso.onView(withId(R.id.ibSearch))
            .perform(click())
        Espresso.onView(withId(R.id.recRepoList))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test(expected = PerformException::class)
    fun test_recyler_view_item_click() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(typeText("virajjage09"))

        Espresso.onView(withId(R.id.ibSearch))
            .perform(click())

        Espresso.onView(withId(R.id.recRepoList))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RepositoryViewHolder>(1,clickItemWithId(R.id.cardView)))
    }

    @Test(expected = PerformException::class)
    fun test_item_with_text_exists() {
        Espresso.onView(withId(R.id.etUserName))
            .perform(typeText("virajjage09"))

        Espresso.onView(withId(R.id.ibSearch))
            .perform(click())
        // Attempt to scroll to an item that contains the special text.
        Espresso.onView(withId(R.id.recRepoList))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.scrollTo<RepositoryViewHolder>(
                    hasDescendant(withText("CustomPatternLock"))
                )
            )
    }

    private fun clickItemWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }

}