package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz8Activity : FragmentActivity(), Dz8ListFragment.Listener, Dz8DetailFragment.Listener, Dz8EditFragment.Listener {

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
            resetFragment()
        }
        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    override fun onStudentClicked(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(extraContainerId, Dz8DetailFragment.getInstance(id), Dz8DetailFragment.TAG)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDeleteClicked() {
        val transaction = supportFragmentManager.beginTransaction()
        (supportFragmentManager.findFragmentByTag(Dz8ListFragment.TAG) as? Dz8ListFragment)?.updateRecycle()
        if (isLandscape) {
            supportFragmentManager.findFragmentByTag(Dz8DetailFragment.TAG)?.apply { transaction.remove(this) }
            transaction.replace(R.id.dz8ContainerTwo, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }

        transaction.commit()
    }

    override fun onEditClicked(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(extraContainerId, Dz8EditFragment.getInstance(id), Dz8EditFragment.TAG)
        transaction.commit()
    }

    override fun onSaveClicked() {
        val transaction = supportFragmentManager.beginTransaction()

        (supportFragmentManager.findFragmentByTag(Dz8ListFragment.TAG) as? Dz8ListFragment)?.updateRecycle()

        if (isLandscape) {
            supportFragmentManager.findFragmentByTag(Dz8EditFragment.TAG)?.apply { transaction.remove(this) }
        } else {
            supportFragmentManager.popBackStack()
        }
        resetFragment()
        transaction.commit()
    }

    override fun onPlusClicked() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(extraContainerId, Dz8EditFragment.getInstance(), Dz8EditFragment.TAG)
        transaction.commit()
    }

    private fun resetFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dz8ContainerOne, Dz8ListFragment(), Dz8ListFragment.TAG)
        transaction.commit()
    }
}