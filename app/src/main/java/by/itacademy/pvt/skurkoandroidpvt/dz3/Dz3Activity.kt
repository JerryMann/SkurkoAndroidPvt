package by.itacademy.pvt.skurkoandroidpvt.dz3

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.transform.Circular
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class Dz3Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)

        val imageView = findViewById<ImageView>(R.id.avatar)
        val url = getString(R.string.url_sample)

        Picasso.get()
            .load(url)
            .transform(Circular())
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    return
                }

                override fun onError(e: Exception?) {
                    imageView.background = getDrawable(R.drawable.error_image_icon_23)
                }
            })
    }
}
