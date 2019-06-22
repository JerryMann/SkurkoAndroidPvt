package by.itacademy.pvt.skurkoandroidpvt.dzMenu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.Dz0activity
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz1.Dz1Activity
import kotlinx.android.synthetic.main.activity_menu.*

class DzMenu : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button1.setOnClickListener {
            val intent = Intent(this, Dz0activity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, Dz1Activity::class.java)
            startActivity(intent)
        }
    }
}
