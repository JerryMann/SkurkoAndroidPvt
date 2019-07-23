package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVVM

sealed class Dz11State {
    object Loading : Dz11State()
    object Loaded : Dz11State()
    class Error(val throwable: Throwable) : Dz11State()
}