package by.itacademy.pvt.skurkoandroidpvt

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dz0.*

class Dz0activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz0)

        button.setOnClickListener {
            swapValues()
        }
        textView1.setOnClickListener {
            swapValues()
        }
        textView2.setOnClickListener {
            swapValues()
        }
    }

    private fun swapValues() {
        val tempText = textView1.text
        textView1.text = textView2.text
        textView2.text = tempText
        val tempColor = textView1.background
        textView1.background = textView2.background
        textView2.background = tempColor
    }
}
