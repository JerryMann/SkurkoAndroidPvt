package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage
import kotlinx.android.synthetic.main.fragment_detail_dz8.*

class Dz11DetailFragment : Fragment(), Dz11DetailView {

    companion object {
        private const val ID_KEY = "KEY"
        val TAG = Dz11ListFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz11DetailFragment {
            val fragment = Dz11DetailFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var clickListener: Listener? = null
    private lateinit var presenter: Dz11DetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentId = arguments?.getString(ID_KEY, " ")

        presenter = Dz11DetailPresenter()
        presenter.setView(this)

        if (studentId != null) {
            presenter.loadStudent(studentId)
        } else {
            activity?.supportFragmentManager?.popBackStack()
        }

        dz8_detail_delete.setOnClickListener {
            if (studentId != null)
                presenter.deleteStudent(studentId)
            clickListener?.onDeleteClicked()
        }

        dz8_detail_edit.setOnClickListener {
            if (studentId != null)
                clickListener?.onEditClicked(studentId)
        }
    }

    override fun showStudent(student: Student?) {
        if (student != null) {
            context?.let { loadCircularImage(student.imageUrl, dz8_detail_image) }
            dz8_detail_name.text = student.name
            dz8_detail_age.text = student.age.toString()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDestroyed()
    }

    interface Listener {
        fun onEditClicked(id: String)
        fun onDeleteClicked()
    }
}