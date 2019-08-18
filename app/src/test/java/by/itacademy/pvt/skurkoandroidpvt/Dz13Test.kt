package by.itacademy.pvt.skurkoandroidpvt

import by.itacademy.pvt.skurkoandroidpvt.dz13extra.Dz13Extra
import org.junit.Assert.assertEquals
import org.junit.Test

class Dz13Test {

    private val test = Dz13Extra()

    @Test
    fun successCheckWin() {
        assertEquals(
            1, test.checkWin(
                listOf(
                    listOf("orange"),
                    listOf("apple", "apple"),
                    listOf("banana", "orange", "apple"),
                    listOf("banana")
                ),
                listOf("orange", "apple", "apple", "banana", "orange", "apple", "banana")
            )
        )
    }

    @Test
    fun failureCheckWin() {
        assertEquals(
            0, test.checkWin(
                listOf(
                    listOf("apple", "apricot"),
                    listOf("banana", "anything", "guava"),
                    listOf("papaya", "anything")
                ),
                listOf("banana", "orange", "guava", "apple", "apricot", "papaya", "kiwi")
            )
        )
    }
}