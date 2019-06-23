package by.itacademy.pvt.skurkoandroidpvt.dzMenu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.Dz0Activity
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz1.Dz1Activity
import by.itacademy.pvt.skurkoandroidpvt.dz2.Dz2Activity
import by.itacademy.pvt.skurkoandroidpvt.dz2.Dz2ClassworkActivity
import kotlinx.android.synthetic.main.activity_menu.*

class DzMenu : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button1.setOnClickListener {
            startActivity(Intent(this, Dz0Activity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, Dz1Activity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, Dz2ClassworkActivity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, Dz2Activity::class.java))
        }
    }
}
