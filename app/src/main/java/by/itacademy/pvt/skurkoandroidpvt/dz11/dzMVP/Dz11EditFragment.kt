package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
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

        if (studentId != null)
            presenter.loadStudent(studentId)

        dz8_edit_save.setOnClickListener {
            val newUrl = dz8_urlEditText.text.toString().trim()
            val newName = dz8_nameEditText.text.toString().trim()
            val newAge = dz8_ageEditText.text.toString().toIntOrNull()

            if (newUrl.isEmpty() ||
                newName.isEmpty() ||
                newAge == null
            ) {
                Toast.makeText(context, R.string.dz6_error_empty, Toast.LENGTH_SHORT).show()
            } else if (!URLUtil.isValidUrl(newUrl)) {
                Toast.makeText(context, R.string.dz6_error_url, Toast.LENGTH_SHORT).show()
            } else {

                if (studentId == null) {
                    presenter.createStudent(newUrl, newName, newAge)
                } else {
                    presenter.updateStudent(studentId, newUrl, newName, newAge)
                }
            }
            clickListener?.onSaveClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDestroyed()
    }

    override fun getDetails(student: Student?) {
        if (student != null) {
            dz8_urlEditText.setText(student.imageUrl)
            dz8_nameEditText.setText(student.name)
            dz8_ageEditText.setText(student.age.toString())
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