package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz6StudentListActivity : Activity(), Dz6StudentListAdapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list_dz6)

        val recycleView = findViewById<RecyclerView>(R.id.dz6_recycler)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)

        val items = mutableListOf(
            Student(
                "1", "https://st.kp.yandex.net/images/actor_iphone/iphone360_61567.jpg",
                "Kaley Cuoco", 23
            ),
            Student(
                "2", "http://www.tele.ru/wp-content/uploads/2018/11/74d88c1dfd19ac27503c333ea8862ba3.jpg",
                "Johnny Galecki", 25
            ),
            Student(
                "3", "https://st.kp.yandex.net/images/actor_iphone/iphone360_223588.jpg",
                "James Parsons", 27
            ),
            Student(
                "4", "https://st.kp.yandex.net/images/actor_iphone/iphone360_1085069.jpg",
                "Melissa Rauch", 22
            ),
            Student(
                "5", "https://st.kp.yandex.net/images/actor_iphone/iphone360_1231378.jpg",
                "Kunal Nayyar", 24
            ),
            Student(
                "6", "https://st.kp.yandex.net/images/actor_iphone/iphone360_26550.jpg",
                "Simon Helberg", 26
            )
        )
        recycleView.adapter = Dz6StudentListAdapter(items, this)
    }

    override fun onStudentClick(item: Student) {
    }
}