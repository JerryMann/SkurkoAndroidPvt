package by.itacademy.pvt.skurkoandroidpvt.dz12

import by.itacademy.pvt.skurkoandroidpvt.dz8.Dz8PrefManager

class Dz12ListPresenter {

    private var view: Dz12ListView? = null
    private lateinit var prefsManager: Dz8PrefManager
    private var searchText: String = ""

    fun setView(view: Dz12ListView?) {
        this.view = view
        prefsManager = view?.getPrefsManager()!!
    }

    fun getDatabase(): List<Student> {
        val studentList = Dz12StudentManager.getStudentList()
        if (studentList.isEmpty()) {
            view?.progressBarOn()
            Dz12StudentManager.loadStudentList(object : Callback() {
                override fun returnResult() {
                    view?.progressBarOff()
                    view?.updateDatabase()
                }
            })
        }
        return studentList
    }

    fun getStudentsByFilterName(name: String): List<Student> {
        val studentList = Dz12StudentManager.getStudentList()
        searchText = name
        if (studentList.isEmpty()) {
            view?.progressBarOn()
            Dz12StudentManager.searchByName(searchText, object : Callback() {
                override fun returnResult() {
                    view?.progressBarOff()
                    view?.updateDatabase()
                }
            })
        }
        return studentList
    }

    fun getSearchList(string: String): List<Student> = Dz12StudentManager.searchList(string)

    fun getTextForSearch(): String = prefsManager.getUserText()

    fun saveTextForSearch(text: String) = prefsManager.saveUserText(text)

    fun requirePrefsManager() = view?.requirePrefsManager()

    fun detach() {
        Dz12StudentManager.dispose()
        view = null
    }
}