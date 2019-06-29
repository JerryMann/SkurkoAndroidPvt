package by.itacademy.pvt.skurkoandroidpvt.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz5OwlActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_owl)
    }

    override fun onResume() {
        super.onResume()

        val owlAnimation = findViewById<ImageView>(R.id.dz5Owl)
        (owlAnimation.background as AnimationDrawable).start()
    }

    override fun onPause() {
        super.onPause()

        val owlAnimation = findViewById<ImageView>(R.id.dz5Owl)
        (owlAnimation.background as AnimationDrawable).stop()
    }
}