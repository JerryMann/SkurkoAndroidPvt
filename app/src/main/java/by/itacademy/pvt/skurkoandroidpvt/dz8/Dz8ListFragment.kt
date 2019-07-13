package by.itacademy.pvt.skurkoandroidpvt.dz8

import android.os.Bundle
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

class Dz8ListFragment : Fragment(), Dz6StudentListAdapter.ClickListener {

    private lateinit var dz6Adapter: Dz6StudentListAdapter
    private lateinit var searchText: String

    companion object {
        val TAG = Dz8ListFragment::class.java.canonicalName!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_list_dz8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycleView = view.findViewById<RecyclerView>(R.id.dz6_recycler)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)
        dz6Adapter = Dz6StudentListAdapter(StudentManager.getStudentList(), this)
        recycleView.adapter = dz6Adapter

        /*   searchStudent.addTextChangedListener(object : TextWatcher {

            private var timer = Timer()

            override fun afterTextChanged(sequence: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(500) {
                    searchText = sequence.toString()
                    runOnUiThread {
                        startSearch()
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })  */
    }

    override fun onStudentClick(item: Student) {
    }
}