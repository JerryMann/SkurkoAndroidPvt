package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import android.content.Context
import android.widget.EditText
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import by.itacademy.pvt.skurkoandroidpvt.dz8.Dz8PrefManager

class Dz11ListPresenter {
    private var view: Dz11ListView? = null
    private var context: Context? = null
    private lateinit var prefsManager: Dz8PrefManager

    fun setContext(context: Context) {
        this.context = context
    }

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

    fun initPrefsManager(editText: EditText) {
        prefsManager = Dz8PrefManager(context!!)
        val savedText = prefsManager.getUserText()
        if (savedText.isNotEmpty()) {
            editText.setText(savedText)
            search(savedText)
        }
    }

    fun saveSearchingText(string: String) {
        prefsManager.saveUserText(string)
    }
}