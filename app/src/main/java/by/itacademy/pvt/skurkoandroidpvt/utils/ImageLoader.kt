package by.itacademy.pvt.skurkoandroidpvt.utils

import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.transform.Circular
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun loadImage(url: String, imageView: ImageView) {
    Picasso.get()
        .load(url)
        .error(R.drawable.error_image_icon_23)
        .into(imageView, object : Callback {
            override fun onSuccess() {
                return
            }

            override fun onError(e: Exception?) {
            }
        })
}

fun loadCircularImage(url: String, imageView: ImageView) {
    Picasso.get()
        .load(url)
        .error(R.drawable.error_image_icon_23)
        .transform(Circular())
        .into(imageView, object : Callback {
            override fun onSuccess() {
                return
            }

            override fun onError(e: Exception?) {
            }
        })
}