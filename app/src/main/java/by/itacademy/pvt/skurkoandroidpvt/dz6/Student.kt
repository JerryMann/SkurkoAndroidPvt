package by.itacademy.pvt.skurkoandroidpvt.dz6

class Student(
    val imageUrl: String,
    val name: String,
    val age: Int
) {
    val id = System.currentTimeMillis().toString()
}