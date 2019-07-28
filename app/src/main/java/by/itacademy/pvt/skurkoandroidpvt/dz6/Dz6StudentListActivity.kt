package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import kotlinx.android.synthetic.main.activity_student_list_dz6.*
import java.util.Timer
import kotlin.concurrent.schedule

class Dz6StudentListActivity : Activity(), Dz6StudentListAdapter.ClickListener {

    private lateinit var dz6Adapter: Dz6StudentListAdapter
    private lateinit var searchText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list_dz6)

        val recycleView = findViewById<RecyclerView>(R.id.dz6Recycler)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)
        dz6Adapter = Dz6StudentListAdapter(StudentManager.getStudentList(), this)
        recycleView.adapter = dz6Adapter

        searchStudent.addTextChangedListener(object : TextWatcher {

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
        })

        dz6AddStudent.setOnClickListener {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentListActivity, ""))
            finish()
        }
    }

    override fun onStudentClick(item: Student) {
        startActivity(Dz6StudentDetailActivity.getIntent(this@Dz6StudentListActivity, item.id))
        finish()
    }

    private fun startSearch() {
        dz6Adapter.updateList(StudentManager.findStudents(searchText))
    }
}
