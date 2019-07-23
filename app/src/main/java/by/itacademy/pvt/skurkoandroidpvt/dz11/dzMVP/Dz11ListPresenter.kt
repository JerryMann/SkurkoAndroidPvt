package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager

class Dz11ListPresenter {
    private var view: Dz11ListView? = null

    fun setView(view: Dz11ListView) {
        this.view = view
    }

    fun onViewDestroyed() {
        this.view = null
    }

    fun search(name: String) {
        view?.showList(StudentManager.findStudents(name))
    }

    fun loadStudentList() {
        view?.showList(StudentManager.getStudentList())
    }
}