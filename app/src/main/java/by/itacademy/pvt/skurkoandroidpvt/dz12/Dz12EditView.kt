package by.itacademy.pvt.skurkoandroidpvt.dz12

interface Dz12EditView {

    fun showStudentInformation(urlLink: String, name: String, age: Int)
    fun getLink(): String
    fun getName(): String
    fun getAge(): Int?
    fun showErrorToast()
    fun backToMainFragment()
}