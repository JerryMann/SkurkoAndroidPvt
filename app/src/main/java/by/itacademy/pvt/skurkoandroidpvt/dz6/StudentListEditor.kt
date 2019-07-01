package by.itacademy.pvt.skurkoandroidpvt.dz6

object StudentListEditor {

    private lateinit var studentList: MutableList<Student>

    fun getStudentList(): MutableList<Student> {
        if (studentList.isEmpty()) {
            createStudentList()
        }
        return studentList
    }

    private fun createStudentList(): MutableList<Student> {
        studentList = mutableListOf(
            Student(
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_61567.jpg",
                "Kaley Cuoco", 23
            ),
            Student(
                "http://www.tele.ru/wp-content/uploads/2018/11/74d88c1dfd19ac27503c333ea8862ba3.jpg",
                "Johnny Galecki", 25
            ),
            Student(
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_223588.jpg",
                "James Parsons", 27
            ),
            Student(
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_1085069.jpg",
                "Melissa Rauch", 22
            ),
            Student(
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_1231378.jpg",
                "Kunal Nayyar", 24
            ),
            Student(
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_26550.jpg",
                "Simon Helberg", 26
            )
        )
        return studentList
    }
}