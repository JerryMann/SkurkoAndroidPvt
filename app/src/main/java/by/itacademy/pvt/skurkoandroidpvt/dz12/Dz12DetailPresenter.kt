package by.itacademy.pvt.skurkoandroidpvt.dz12

class Dz12DetailPresenter {

    private var view: Dz12DetailView? = null
    private lateinit var student: Student

    fun setView(view: Dz12DetailView?) {
        this.view = view
    }

    fun getStudent(id: String) {
        student = Dz12StudentManager.getStudentById(id)!!
    }

    fun showStudent() {
        val imageUrl = student.imageUrl
        val name = student.name
        val age = student.age.toString()
        view?.showStudent(imageUrl, name, age)
    }

    fun deleteStudent() = Dz12StudentManager.deleteStudentById(student)

    fun detach() {
        view = null
    }
}