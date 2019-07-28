package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz6.Dz6StudentListAdapter
import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import kotlinx.android.synthetic.main.fragment_student_list_dz8.*
import java.util.Timer
import kotlin.concurrent.schedule

class Dz8ListFragment : Fragment(), Dz6StudentListAdapter.ClickListener {

    private lateinit var prefManager: Dz8PrefManager
    private lateinit var dz6Adapter: Dz6StudentListAdapter
    private var searchText: String = ""
    private var clickListener: Listener? = null

    companion object {
        val TAG = Dz8ListFragment::class.java.canonicalName!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_list_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycleView = view.findViewById<RecyclerView>(R.id.dz8Recycler)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)
        dz6Adapter = Dz6StudentListAdapter(StudentManager.getStudentList(), this)
        recycleView.adapter = dz6Adapter

        dz8AddStudent.setOnClickListener {
            clickListener?.onPlusClicked()
        }

        searchStudent.addTextChangedListener(object : TextWatcher {

            private var timer = Timer()

            override fun afterTextChanged(sequence: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(500) {
                    searchText = sequence.toString()
                    activity?.runOnUiThread {
                        updateRecycle()
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prefManager = Dz8PrefManager(requireContext())
    }

    override fun onPause() {
        super.onPause()
        prefManager.saveUserText(searchText)
    }

    override fun onResume() {
        super.onResume()
        val savedText = prefManager.getUserText()
        if (savedText != searchText) {
            searchText = savedText
            searchStudent.setText(savedText)
            updateRecycle()
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

    override fun onStudentClick(item: Student) {
        clickListener?.onStudentClicked(item.id)
    }

    fun updateRecycle() {
        dz6Adapter.updateList(StudentManager.findStudents(searchText))
    }

    interface Listener {
        fun onStudentClicked(id: String)
        fun onPlusClicked()
    }
}