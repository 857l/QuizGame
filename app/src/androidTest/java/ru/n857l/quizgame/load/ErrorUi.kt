package ru.n857l.quizgame.load

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

class ErrorUi(containerIdMatcher: Matcher<View>, classTypeMatcher: Matcher<View>) {

    private val viewId = R.id.errorTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            withId(viewId),
            withText(R.string.no_internet_connection),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun waitTillVisible() {
        onView(isRoot()).perform(waitTillDisplayed(viewId, 4000))
    }

    fun waitTillDoesntExist() {
        onView(isRoot()).perform(waitTillDoesntExist(viewId, 4000))
    }
}
