package by.itacademy.pvt.skurkoandroidpvt.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.transform.Circular
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz2.*
import java.lang.Exception

class Dz2Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        val imageView = findViewById<ImageView>(R.id.image)
        val url = text.text

        button.setOnClickListener {
            progressCircular.visibility = View.VISIBLE
            Picasso.get()
                .load(url.toString())
                .transform(Circular())
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        progressCircular.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressCircular.visibility = View.GONE
                        imageView.background = getDrawable(R.drawable.error_image_icon_23)
                    }
                })
        }
    }
}