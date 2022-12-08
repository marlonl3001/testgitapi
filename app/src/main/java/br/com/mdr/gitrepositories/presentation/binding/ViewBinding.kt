package br.com.mdr.gitrepositories.presentation.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.mdr.gitrepositories.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text?.let {
            val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            snackBar.setBackgroundTint(view.context.getColor(R.color.colorPrimary))
            snackBar.show()
        }
    }

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun ImageView.bindLoadCircleImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
