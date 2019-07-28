package by.itacademy.pvt.skurkoandroidpvt.dz5

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz5DiagramActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5_diagram)

        val dataSample = floatArrayOf(26f, 12f, 10f, 40f, 22f)
        val diagramView = findViewById<Dz5DiagramView>(R.id.dz5DiagramView)

        diagramView.diagramValues = dataSample
    }
}