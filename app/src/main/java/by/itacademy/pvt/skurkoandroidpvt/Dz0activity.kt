package by.itacademy.pvt.skurkoandroidpvt

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Dz0activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz0)

        var text1 = R.string.fizz
        var text2 = R.string.buzz
        var color1 = resources.getColor(R.color.oneViewColor)
        var color2 = resources.getColor(R.color.twoViewColor)
        var button = findViewById<Button>(R.id.button)
        var textView1 = findViewById<TextView>(R.id.textView1)
        var textView2 = findViewById<TextView>(R.id.textView2)

        fun swapValues() {
            var tempText = text1
            text1 = text2
            text2 = tempText
            textView1.setText(text1)
            textView2.setText(text2)
            var tempColor = color1
            color1 = color2
            color2 = tempColor
            textView1.setBackgroundColor(color1)
            textView2.setBackgroundColor(color2)
        }

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
}
