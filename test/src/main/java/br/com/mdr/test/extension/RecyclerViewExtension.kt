package br.com.mdr.test.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers

fun <T : RecyclerView.ViewHolder> Int.recyclerViewClickItem(position: Int): ViewInteraction =
    Espresso.onView(ViewMatchers.withId(this))
        .perform(RecyclerViewActions.actionOnItemAtPosition<T>(position, ViewActions.click()))

fun <T : RecyclerView.ViewHolder> Int.scrollToPositionRecyclerView(position: Int): ViewInteraction =
    Espresso.onView(ViewMatchers.withId(this))
        .perform(RecyclerViewActions.scrollToPosition<T>(position))
