package by.itacademy.pvt.skurkoandroidpvt.dz3

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage

class Dz3Activity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)

        val imageView = findViewById<ImageView>(R.id.avatar)
        val url = getString(R.string.url_sample)

        loadCircularImage(url, imageView)
    }
}
