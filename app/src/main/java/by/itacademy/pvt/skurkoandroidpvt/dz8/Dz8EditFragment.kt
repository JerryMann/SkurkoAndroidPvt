package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import kotlinx.android.synthetic.main.fragment_edit_dz8.*

class Dz8EditFragment : Fragment() {

    companion object {
        private const val ID_KEY = "KEY"
        val TAG = Dz8ListFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz8EditFragment {
            val fragment = Dz8EditFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var clickListener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentId = arguments?.getString(ID_KEY, null)
        val student: Student? = studentId?.let { StudentManager.getStudent(it) }
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