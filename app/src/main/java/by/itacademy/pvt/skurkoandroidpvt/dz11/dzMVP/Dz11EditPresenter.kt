package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVP

import android.content.Context
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.Toast
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz6.Student
import by.itacademy.pvt.skurkoandroidpvt.dz6.StudentManager
import kotlin.random.Random

class Dz11EditPresenter {
    private var view: Dz11EditView? = null
    private var context: Context? = null

    fun setContext(context: Context) {
        this.context = context
    }

    fun setView(view: Dz11EditView) {
        this.view = view
    }

    fun onViewDestroyed() {
        this.view = null
    }

    fun loadStudent(id: String) {
        val student: Student? = StudentManager.getStudent(id)
        view?.showStudent(student)
    }

    private fun createStudent(url: String, name: String, age: Int) {
        StudentManager.createStudent(
            Student(
                Random(System.currentTimeMillis()).toString(),
                url,
                name,
                age
            )
        )
    }

    private fun updateStudent(id: String, url: String, name: String, age: Int) {
        StudentManager.updateStudent(Student(id, url, name, age))
    }

    fun saveStudent(
        id: String?,
        url: EditText,
        name: EditText,
        age: EditText
    ) {
        val tempUrl = url.text.toString().trim()
        val tempName = name.text.toString().trim()
        val tempAge = age.text.toString().toIntOrNull()

        if (tempUrl.isEmpty() ||
            tempName.isEmpty() ||
            tempAge == null
        ) {
            Toast.makeText(context, R.string.dz6_error_empty, Toast.LENGTH_SHORT).show()
        } else if (!URLUtil.isValidUrl(tempUrl)) {
            Toast.makeText(context, R.string.dz6_error_url, Toast.LENGTH_SHORT).show()
        } else {
            if (id == null) {
                createStudent(tempUrl, tempName, tempAge)
            } else {
                updateStudent(id, tempUrl, tempName, tempAge)
            }
        }
    }
}