package by.itacademy.pvt.skurkoandroidpvt.dzMenu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.Dz0Activity
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz1.Dz1Activity
import by.itacademy.pvt.skurkoandroidpvt.dz2.Dz2Activity
import by.itacademy.pvt.skurkoandroidpvt.dz2.Dz2ClassworkActivity
import by.itacademy.pvt.skurkoandroidpvt.dz3.Dz3Activity
import by.itacademy.pvt.skurkoandroidpvt.dz4.Dz4Activity
import by.itacademy.pvt.skurkoandroidpvt.dz5.Dz5DiagramActivity
import by.itacademy.pvt.skurkoandroidpvt.dz5.Dz5OwlActivity
import by.itacademy.pvt.skurkoandroidpvt.dz6.Dz6StudentListActivity
import by.itacademy.pvt.skurkoandroidpvt.dz8.Dz8Activity
import by.itacademy.pvt.skurkoandroidpvt.dz9.Dz9Activity
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

        button5.setOnClickListener {
            startActivity(Intent(this, Dz3Activity::class.java))
        }

        button6.setOnClickListener {
            startActivity(Intent(this, Dz4Activity::class.java))
        }

        button7.setOnClickListener {
            startActivity(Intent(this, Dz5OwlActivity::class.java))
        }

        button8.setOnClickListener {
            startActivity(Intent(this, Dz5DiagramActivity::class.java))
        }

        button9.setOnClickListener {
            startActivity(Intent(this, Dz6StudentListActivity::class.java))
        }

        button10.setOnClickListener {
            startActivity(Intent(this, Dz8Activity::class.java))
        }

        button11.setOnClickListener {
            startActivity(Intent(this, Dz9Activity::class.java))
        }
    }
}
