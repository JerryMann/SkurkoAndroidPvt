package by.itacademy.pvt.skurkoandroidpvt.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.transform.Circular
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        val imageView = findViewById<ImageView>(R.id.image)
        val url = text.text

        button.setOnClickListener {
            progress_circular.visibility = View.VISIBLE
            Picasso.get()
                .load(url.toString())
                .transform(Circular())
                .into(imageView)
            progress_circular.visibility = View.GONE
        }
    }
}