package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage
import kotlinx.android.synthetic.main.fragment_detail_dz8.*

class Dz8DetailFragment : Fragment() {

    companion object {
        private const val ID_KEY = "KEY"
        val TAG = Dz8ListFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz8DetailFragment {
            val fragment = Dz8DetailFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var clickListener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentId = arguments?.getString(ID_KEY, null)
        val student: Student? = studentId?.let { StudentManager.getStudent(it) }
        val avatarImageView = view.findViewById<ImageView>(R.id.dz8DetailImage)

        if (student != null) {
            context?.let { loadCircularImage(student.imageUrl, avatarImageView) }
            dz8DetailName.text = student.name
            dz8DetailAge.text = student.age.toString()
        }

        dz8DetailDelete.setOnClickListener {
            if (student == null) {
                Toast.makeText(context, R.string.dz6_wrong_id, Toast.LENGTH_SHORT).show()
            } else {
                StudentManager.deleteStudent(studentId)
            }
            clickListener?.onDeleteClicked()
        }

        dz8DetailEdit.setOnClickListener {
            if (student == null) {
                Toast.makeText(context, R.string.dz6_wrong_id, Toast.LENGTH_SHORT).show()
            } else {
                clickListener?.onEditClicked(studentId)
            }
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
        fun onEditClicked(id: String)
        fun onDeleteClicked()
    }
}