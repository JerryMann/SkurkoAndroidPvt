package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz8Activity : FragmentActivity(), Dz8ListFragment.Listener, Dz8DetailFragment.Listener {

    private var isLandscape: Boolean = false

    private val extraContainerId: Int
        get() {
            return if (isLandscape) {
                R.id.dz8ContainerTwo
            } else {
                R.id.dz8ContainerOne
            }
        }

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

    override fun onStudentClicked(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(extraContainerId, Dz8DetailFragment.getInstance(id), Dz8DetailFragment.TAG)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onStudentDeleteClicked() {
        val transaction = supportFragmentManager.beginTransaction()

        (supportFragmentManager.findFragmentByTag(Dz8ListFragment.TAG) as? Dz8ListFragment)?.startSearch()

        if (isLandscape) {
            supportFragmentManager.findFragmentByTag(Dz8DetailFragment.TAG)?.apply { transaction.remove(this) }
            transaction.replace(R.id.dz8ContainerTwo, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }

        transaction.commit()
    }

    override fun onStudentEditClicked(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(extraContainerId, Dz8EditFragment.getInstance(id), Dz8EditFragment.TAG)
        transaction.commit()
    }
}