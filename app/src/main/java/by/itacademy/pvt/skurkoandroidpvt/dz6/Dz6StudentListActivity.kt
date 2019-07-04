package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import kotlinx.android.synthetic.main.activity_student_list_dz6.*

class Dz6StudentListActivity : Activity(), Dz6StudentListAdapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list_dz6)

        val recycleView = findViewById<RecyclerView>(R.id.dz6_recycler)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = Dz6StudentListAdapter(StudentManager.getStudentList(), this)

        dz6_add_student.setOnClickListener {
            startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentListActivity, ""))
            finish()
        }
    }

    override fun onStudentClick(item: Student) {
        startActivity(Dz6StudentDetailActivity.getIntent(this@Dz6StudentListActivity, item.id))
        finish()
    }
}
