package by.itacademy.pvt.skurkoandroidpvt.dz12

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz12Activity : FragmentActivity(), Dz12ListFragment.Listener, Dz12DetailFragment.Listener, Dz12EditFragment.Listener {

    private var isLandscape: Boolean = false

    private val detailsContainerResId: Int
        get() {
            return if (isLandscape) {
                R.id.dz12ContainerTwo
            } else {
                R.id.dz12ContainerOne
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz12)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz12ContainerOne, Dz12ListFragment(), Dz12ListFragment.TAG)
            transaction.commit()
        }
    }

    override fun onStudentClick(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(detailsContainerResId, Dz12DetailFragment.getInstance(id), Dz12DetailFragment.TAG)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onFABClick() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(detailsContainerResId, Dz12EditFragment.getInstance(), Dz12EditFragment.TAG)
        transaction.commit()
    }

    override fun onDeleteStudentClick() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isLandscape) {
            (supportFragmentManager.findFragmentByTag(Dz12ListFragment.TAG) as? Dz12ListFragment)?.updateList()
            supportFragmentManager.findFragmentByTag(Dz12DetailFragment.TAG)?.apply { transaction.remove(this) }
            transaction.replace(R.id.dz12ContainerTwo, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }

        transaction.commit()
    }

    override fun onEditStudentClick(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(detailsContainerResId, Dz12EditFragment.getInstance(id), Dz12EditFragment.TAG)
        transaction.commit()
    }

    override fun onSaveStudentClick() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isLandscape) {
            (supportFragmentManager.findFragmentByTag(Dz12ListFragment.TAG) as? Dz12ListFragment)?.updateList()
            supportFragmentManager.findFragmentByTag(Dz12EditFragment.TAG)?.apply { transaction.remove(this) }
        } else {
            transaction.replace(detailsContainerResId, Dz12ListFragment(), Dz12ListFragment.TAG)
        }

        transaction.commit()
    }
}