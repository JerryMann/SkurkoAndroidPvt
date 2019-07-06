package by.itacademy.pvt.skurkoandroidpvt.dz6

import kotlin.random.Random

object StudentManager {

    private var studentList: MutableList<Student> = mutableListOf()

    fun getStudentList(): MutableList<Student> {
        if (studentList.isEmpty()) {
            createStudentList()
        }
        return studentList
    }

    private fun createStudentList(): MutableList<Student> {
        studentList = mutableListOf(
            Student(
                Random(System.currentTimeMillis()).toString(),
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_61567.jpg",
                "Kaley Cuoco",
                23
            ),
            Student(
                Random(System.currentTimeMillis()).toString(),
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_13258.jpg",
                "Johnny Galecki",
                25
            ),
            Student(
                Random(System.currentTimeMillis()).toString(),
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_223588.jpg",
                "James Parsons",
                27
            ),
            Student(
                Random(System.currentTimeMillis()).toString(),
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_1085069.jpg",
                "Melissa Rauch",
                22
            ),
            Student(
                Random(System.currentTimeMillis()).toString(),
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_1231378.jpg",
                "Kunal Nayyar",
                24
            ),
            Student(
                Random(System.currentTimeMillis()).toString(),
                "https://st.kp.yandex.net/images/actor_iphone/iphone360_26550.jpg",
                "Simon Helberg",
                26
            )
        )
        return studentList
    }

    fun getStudent(id: String): Student? {
        return studentList.find { it.id == id }
    }

    fun deleteStudent(id: String) {
        studentList.find { it.id == id }?.apply { studentList.remove(this) }
    }

    fun updateStudent(student: Student) {
        val index = studentList.indexOfFirst { it.id == student.id }
        if (index != -1) {
            studentList[index] = student
        }
    }

    fun createStudent(student: Student) {
        studentList.add(student)
    }

    fun findStudents(query: String): List<Student> {
        return studentList.filter { it.name.contains(query, true) }
    }
}