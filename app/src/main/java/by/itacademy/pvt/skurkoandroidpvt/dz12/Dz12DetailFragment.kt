package by.itacademy.pvt.skurkoandroidpvt.dz12

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage
import kotlinx.android.synthetic.main.fragment_detail_dz12.*

class Dz12DetailFragment : Fragment(), Dz12DetailView {

    companion object {

        private const val ID_KEY = "id_key"
        val TAG = Dz12DetailFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz12DetailFragment {
            val fragment = Dz12DetailFragment()
            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val presenter = Dz12DetailPresenter()
    private var listener: Listener? = null
    private var idStudent: String = " "
    private lateinit var imageView: ImageView
    private lateinit var nameView: TextView
    private lateinit var ageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idStudent = arguments?.getString(ID_KEY, " ") ?: " "
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_dz12, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.setView(this)
        presenter.getStudent(idStudent)

        imageView = dz12DetailImage
        nameView = dz12DetailName
        ageView = dz12DetailAge

        presenter.showStudent()

        dz12DetailDelete.setOnClickListener {
            presenter.deleteStudent()
            listener?.onDeleteStudentClick()
        }

        dz12DetailEdit.setOnClickListener {
            listener?.onEditStudentClick(idStudent)
        }
    }

    override fun showStudent(imageUrl: String, name: String, age: String) {
        loadCircularImage(imageUrl, imageView)
        nameView.text = name
        ageView.text = age
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
        fun onDeleteStudentClick()
        fun onEditStudentClick(id: String)
    }
}