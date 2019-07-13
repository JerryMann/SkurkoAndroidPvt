package by.itacademy.pvt.skurkoandroidpvt.dz8

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
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import kotlinx.android.synthetic.main.fragment_edit_dz8.*
import kotlin.random.Random

class Dz8EditFragment : Fragment() {

    companion object {
        private const val ID_KEY = "KEY"
        val TAG = Dz8ListFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz8EditFragment {
            val fragment = Dz8EditFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    private var clickListener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentId = arguments?.getString(ID_KEY, null)
        val item: Student? = studentId?.let { StudentManager.getStudent(it) }
        if (item != null) {
            dz8_urlEditText.setText(item.imageUrl)
            dz8_nameEditText.setText(item.name)
            dz8_ageEditText.setText(item.age.toString())
        }

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

                if (item == null) {
                    StudentManager.createStudent(
                        Student(
                            Random(System.currentTimeMillis()).toString(),
                            newUrl,
                            newName,
                            newAge
                        )
                    )
                } else {
                    StudentManager.updateStudent(Student(item.id, newUrl, newName, newAge))
                }
            }
            clickListener?.onSaveClicked()
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