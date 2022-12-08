package br.com.mdr.test.matchers

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withRecyclerView(recyclerViewId: Int) =
    RecyclerViewMatcher(recyclerViewId)

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    private fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            private var resources: Resources? = null
            private var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()
                resources?.let {
                    idDescription = try {
                        it.getResourceName(recyclerViewId)
                    } catch (ex: Resources.NotFoundException) {
                        String.format("%s (resource name not found)", Integer.valueOf(recyclerViewId))
                    }
                }

                description.appendText("with id: $idDescription")
            }

            override fun matchesSafely(view: View): Boolean {
                if (childView == null) {
                    val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                    childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                }

                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView?.findViewById(targetViewId) as View?
                    view === targetView
                }
            }
        }
    }
}
