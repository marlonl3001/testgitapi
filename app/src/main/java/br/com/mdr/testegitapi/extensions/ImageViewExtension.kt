package br.com.mdr.testegitapi.extensions

import android.widget.ImageView
import br.com.mdr.testegitapi.R
import com.squareup.picasso.Picasso

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */

fun ImageView.loadWith(url: String?) {
    if (url.isNullOrEmpty())
        setImageResource(R.drawable.git)
    else
        Picasso.with(context).load(url).fit().into(this)
}