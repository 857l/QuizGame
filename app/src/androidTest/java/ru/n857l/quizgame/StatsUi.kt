package ru.n857l.quizgame

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.CoreMatchers.allOf

class StatsUi(
    incorrects: Int,
    corrects: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction =
        onView(
            allOf(
                withId(R.id.statsTextView),
                isAssignableFrom(TextView::class.java),
                withText("Corrects: $corrects\nIncorrects: $incorrects"),
                containerIdMatcher,
                containerClassTypeMatcher
            )
        )

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }
}
