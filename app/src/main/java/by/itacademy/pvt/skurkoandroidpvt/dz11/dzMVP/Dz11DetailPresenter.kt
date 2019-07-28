package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager

class Dz11DetailPresenter {

    private var view: Dz11DetailView? = null

    fun setView(view: Dz11DetailView) {
        this.view = view
    }

    fun onViewDestroyed() {
        this.view = null
    }

    fun loadStudent(id: String) {
        val student: Student? = StudentManager.getStudent(id)
        view?.showStudent(student)
    }

    fun deleteStudent(id: String) {
        StudentManager.deleteStudent(id)
    }
}