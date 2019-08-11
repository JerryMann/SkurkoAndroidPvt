package by.itacademy.pvt.skurkoandroidpvt.dz12

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object Dz12StudentManager {
    private const val PAGE_SIZE = 100

    private var studentsList: MutableList<Student> = mutableListOf()

    private var searchText: String = ""

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    fun getStudentList(): MutableList<Student> = studentsList

    fun loadStudentList(callback: Callback) {
        disposable = repository
            .getAll(PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                studentsList = it
                callback.returnResult()
            }
    }

    fun searchByName(name: String, callback: Callback) {
        searchText = name
        disposable = repository
            .getByFilterName(name, PAGE_SIZE, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                studentsList = it
                callback.returnResult()
            }
    }

    fun getStudentById(id: String): Student? {
        return studentsList.find { it.id == id }
    }

    fun deleteStudentById(student: Student) {
        studentsList.remove(student)
        disposable = repository
            .deleteStudent(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun updateStudent(newStudent: Student) {
        val id = newStudent.id
        val oldStudent = studentsList.find { it.id == id }
        val index = studentsList.indexOf(oldStudent)
        studentsList[index] = newStudent
        disposable = repository
            .updateStudent(newStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun addNewStudent(student: Student) {
        studentsList.add(student)
        disposable = repository
            .saveNewStudent(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val index = studentsList.indexOf(student)
                studentsList[index] = it
            }
    }

    fun searchList(string: String): List<Student> {
        return studentsList.filter { it.name.contains(string, true) }
    }

    fun getId(): String {
        return System.currentTimeMillis().toString()
    }

    fun dispose() {
        disposable?.dispose()
    }
}