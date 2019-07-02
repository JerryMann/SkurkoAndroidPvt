package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.R
import kotlinx.android.synthetic.main.activity_dz6_detail.*

class Dz6StudentDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_detail)

        dz6_detail_edit.setOnClickListener {
            startActivity(Intent(this, Dz6StudentEditActivity::class.java))
        }

        dz6_detail_delete.setOnClickListener {
            startActivity(Intent(this, Dz6StudentListActivity::class.java))
        }
    }
}