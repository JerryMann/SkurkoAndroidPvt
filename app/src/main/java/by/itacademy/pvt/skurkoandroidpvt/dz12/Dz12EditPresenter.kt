package by.itacademy.pvt.skurkoandroidpvt.dz12

class Dz12EditPresenter {

    private var view: Dz12EditView? = null
    private lateinit var student: Student

    fun setView(view: Dz12EditView?) {
        this.view = view
    }

    fun showStudentInformation(id: String) {
        if (id.isNotBlank()) {
            student = Dz12StudentManager.getStudentById(id)!!
            val urlLink = student.imageUrl
            val name = student.name
            val age = student.age
            view?.showStudentInformation(urlLink, name, age)
        }
    }

    fun onSaveButtonClick(id: String) {
        val urlLink = view?.getLink()!!
        val name = view?.getName()!!
        val age = view?.getAge()
        if (checkCorrectEnterFields(urlLink, name, age)) {
            if (id.isBlank()) {
                saveNewStudent(urlLink, name, age!!)
            } else {
                updateStudent(id, urlLink, name, age!!)
            }
        } else {
            view?.showErrorToast()
        }
    }

    private fun isUrl(url: String): Boolean {
        if (url.startsWith("http", true)) return true
        if (url.startsWith("www", true)) return true
        return false
    }

    private fun checkCorrectEnterFields(urlLink: String, name: String, age: Int?): Boolean {
        var isEnterRight = true

        if (!isUrl(urlLink)) isEnterRight = false

        if (name.isBlank()) isEnterRight = false

        if (age == null) isEnterRight = false

        return isEnterRight
    }

    private fun updateStudent(id: String, urlLink: String, name: String, age: Int) {
        val newStudent = Student(id, urlLink, name, age)
        Dz12StudentManager.updateStudent(newStudent)
        view?.backToMainFragment()
    }

    private fun saveNewStudent(urlLink: String, name: String, age: Int) {
        val id = Dz12StudentManager.getId()
        val newStudent = Student(id, urlLink, name, age)
        Dz12StudentManager.addNewStudent(newStudent)
        view?.backToMainFragment()
    }

    fun detach() {
        view = null
    }
}