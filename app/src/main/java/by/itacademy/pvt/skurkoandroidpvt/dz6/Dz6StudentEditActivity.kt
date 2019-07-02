package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.R
import kotlinx.android.synthetic.main.activity_dz6_edit.*

class Dz6StudentEditActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_edit)

        dz6_edit_save.setOnClickListener {
            startActivity(Intent(this, Dz6StudentListActivity::class.java))
        }
    }
}