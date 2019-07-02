package by.itacademy.pvt.skurkoandroidpvt.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz5OwlActivity : Activity() {

    private lateinit var owlAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_owl)

        owlAnimation = findViewById<ImageView>(R.id.dz5Owl)
            .background as AnimationDrawable
    }

    override fun onResume() {
        super.onResume()
        owlAnimation.start()
    }

    override fun onPause() {
        super.onPause()
        owlAnimation.stop()
    }
}