package by.itacademy.pvt.skurkoandroidpvt

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text1 = R.string.fizz
        var text2 = R.string.buzz
        var tempText = R.string.swap

        var color1 = resources.getColor(R.color.oneViewColor)
        var color2 = resources.getColor(R.color.twoViewColor)
        var tempColor = R.color.buttonColor

        //doing stuff by pressing button
        findViewById<Button>(R.id.button).setOnClickListener {
            //swap text
            tempText = text1
            text1 = text2
            text2 = tempText
            findViewById<TextView>(R.id.textView1).setText(text1)
            findViewById<TextView>(R.id.textView2).setText(text2)


            //swap color in views
            tempColor = color1
            color1 = color2
            color2 = tempColor
            findViewById<TextView>(R.id.textView1).setBackgroundColor(color1)
            findViewById<TextView>(R.id.textView2).setBackgroundColor(color2)
        }

        //doing stuff by pressing on textView
        findViewById<TextView>(R.id.textView1).setOnClickListener {
            //swap text
            tempText = text1
            text1 = text2
            text2 = tempText
            findViewById<TextView>(R.id.textView1).setText(text1)
            findViewById<TextView>(R.id.textView2).setText(text2)


            //swap color in views
            tempColor = color1
            color1 = color2
            color2 = tempColor
            findViewById<TextView>(R.id.textView1).setBackgroundColor(color1)
            findViewById<TextView>(R.id.textView2).setBackgroundColor(color2)
        }

        findViewById<TextView>(R.id.textView2).setOnClickListener {
            //swap text
            tempText = text1
            text1 = text2
            text2 = tempText
            findViewById<TextView>(R.id.textView1).setText(text1)
            findViewById<TextView>(R.id.textView2).setText(text2)


            //swap color in views
            tempColor = color1
            color1 = color2
            color2 = tempColor
            findViewById<TextView>(R.id.textView1).setBackgroundColor(color1)
            findViewById<TextView>(R.id.textView2).setBackgroundColor(color2)
        }

    }

}
