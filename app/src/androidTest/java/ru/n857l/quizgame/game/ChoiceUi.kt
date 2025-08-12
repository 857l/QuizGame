package ru.n857l.quizgame.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import ru.n857l.quizgame.ButtonColorMatcher

class ChoiceUi(
    id: Int,
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
    onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(text),
            isAssignableFrom(AppCompatButton::class.java),
            isDisplayed()
        )
    )
) {
    fun assertAvailableToChooseState() {
        interaction.check(matches(ButtonColorMatcher("#5367B7")))
            .check(matches(isEnabled()))
            .check(matches(isClickable()))

    }

    fun assertNotAvailableToChooseState() {
        interaction.check(matches(isNotEnabled()))
    }

    fun assertCorrectState() {
        interaction.check(matches(ButtonColorMatcher("#13CC2E")))
            .check(matches(isEnabled()))
            .check(matches(isNotClickable()))
    }

    fun assertIncorrectState() {
        interaction.check(matches(ButtonColorMatcher("#DD3A3D")))
            .check(matches(isEnabled()))
            .check(matches(isNotClickable()))
    }

}
