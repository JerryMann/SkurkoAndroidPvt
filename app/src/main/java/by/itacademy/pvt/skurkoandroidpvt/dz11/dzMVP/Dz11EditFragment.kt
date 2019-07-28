package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import kotlinx.android.synthetic.main.fragment_edit_dz8.*

class Dz11EditFragment : Fragment(), Dz11EditView {

    companion object {
        private const val ID_KEY = "KEY"
        val TAG = Dz11ListFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz11EditFragment {
            val fragment = Dz11EditFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    private var clickListener: Listener? = null
    private lateinit var presenter: Dz11EditPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentId = arguments?.getString(ID_KEY, " ")

        presenter = Dz11EditPresenter()
        presenter.setView(this)
        presenter.setContext(view.context)

        if (studentId != null)
            presenter.loadStudent(studentId)

        dz8EditSave.setOnClickListener {
            val newUrl = dz8UrlEditText
            val newName = dz8NameEditText
            val newAge = dz8AgeEditText

            presenter.saveStudent(studentId, newUrl, newName, newAge)
            clickListener?.onSaveClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDestroyed()
    }

    override fun showStudent(student: Student?) {
        if (student != null) {
            dz8UrlEditText.setText(student.imageUrl)
            dz8NameEditText.setText(student.name)
            dz8AgeEditText.setText(student.age.toString())
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

    interface Listener {
        fun onSaveClicked()
    }
}