package by.itacademy.pvt.skurkoandroidpvt.dz12

import by.itacademy.pvt.skurkoandroidpvt.dz8.Dz8PrefManager

interface Dz12ListView {

    fun getPrefsManager(): Dz8PrefManager
    fun requirePrefsManager(): Dz8PrefManager
    fun updateDatabase()
    fun progressBarOn()
    fun progressBarOff()
}