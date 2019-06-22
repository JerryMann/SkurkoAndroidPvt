package by.itacademy.pvt.skurkoandroidpvt.dz_menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.MainActivity
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz1.Dz1Activity
import kotlinx.android.synthetic.main.activity_menu.*

class DzMenu : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button1.setOnClickListener {
            startDz(MainActivity::class.java)
        }

        button2.setOnClickListener {
            startDz(Dz1Activity::class.java)
        }
    }

    private fun <T> startDz(activity: Class<T>) where T: Activity {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

}
