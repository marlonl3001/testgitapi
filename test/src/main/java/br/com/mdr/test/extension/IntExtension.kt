package br.com.mdr.test.extension

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import br.com.mdr.test.matchers.withRecyclerView

fun Int.atPosition(position: Int, text: String): ViewInteraction =
    onView(
        withRecyclerView(
            this
        ).atPosition(
            position = position
        )
    ).check(
        matches(
            ViewMatchers.hasDescendant(
                ViewMatchers.withText(text)
            )
        )
    )

