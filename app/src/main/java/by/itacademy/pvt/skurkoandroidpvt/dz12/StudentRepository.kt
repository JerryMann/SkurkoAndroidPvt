package by.itacademy.pvt.skurkoandroidpvt.dz12

import io.reactivex.Completable
import io.reactivex.Observable

interface StudentRepository {

    fun getAll(pageSize: Int): Observable<MutableList<Student>>

    fun getByFilterName(name: String, pageSize: Int, offset: Int): Observable<MutableList<Student>>

    fun saveNewStudent(student: Student): Observable<Student>

    fun updateStudent(student: Student): Completable

    fun deleteStudent(student: Student): Completable
}