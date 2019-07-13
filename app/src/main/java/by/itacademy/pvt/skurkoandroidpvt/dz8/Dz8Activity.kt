package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz8Activity : FragmentActivity() {

    private var isLandscape: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8ContainerOne, Dz8ListFragment(), Dz8ListFragment.TAG)
            transaction.commit()
        }
        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}