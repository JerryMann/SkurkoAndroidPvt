package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import kotlin.random.Random

class Dz11EditPresenter {
    private var view: Dz11EditView? = null

    fun setView(view: Dz11EditView) {
        this.view = view
    }

    fun onViewDestroyed() {
        this.view = null
    }

    fun loadStudent(id: String) {
        val student: Student? = StudentManager.getStudent(id)
        view?.getDetails(student)
    }

    fun createStudent(url: String, name: String, age: Int) {
        StudentManager.createStudent(
            Student(
                Random(System.currentTimeMillis()).toString(),
                url,
                name,
                age
            )
        )
    }

    fun updateStudent(id: String, url: String, name: String, age: Int) {
        StudentManager.updateStudent(Student(id, url, name, age))
    }
}