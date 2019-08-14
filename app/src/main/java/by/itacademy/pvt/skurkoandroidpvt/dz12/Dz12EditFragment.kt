package by.itacademy.pvt.skurkoandroidpvt.dz12

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.skurkoandroidpvt.R
import kotlinx.android.synthetic.main.fragment_edit_dz12.*

class Dz12EditFragment : Fragment(), Dz12EditView {

    companion object {

        private const val ID_KEY = "id_key"
        val TAG = Dz12EditFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz12EditFragment {
            val fragment = Dz12EditFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    private val presenter = Dz12EditPresenter()
    private var listener: Listener? = null
    private var idStudent: String = " "
    private lateinit var imageUrlText: EditText
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idStudent = arguments?.getString(ID_KEY, " ") ?: " "
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_dz12, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)

        imageUrlText = dz12UrlEditText
        nameText = dz12NameEditText
        ageText = dz12AgeEditText

        presenter.showStudentInformation(idStudent)

        dz12EditSave.setOnClickListener {
            presenter.onSaveButtonClick(idStudent)
        }
    }

    override fun showStudentInformation(urlLink: String, name: String, age: Int) {
        imageUrlText.setText(urlLink)
        nameText.setText(name)
        ageText.setText(age.toString())
    }

    override fun getLink(): String = imageUrlText.text.toString()

    override fun getName(): String = nameText.text.toString()

    override fun getAge(): Int? = ageText.text.toString().toIntOrNull()

    override fun backToMainFragment() {
        listener?.onSaveStudentClick()
    }

    override fun showErrorToast() {
        Toast.makeText(context, getText(R.string.dz6_error_empty), Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        presenter.detach()
    }

    interface Listener {
        fun onSaveStudentClick()
    }
}