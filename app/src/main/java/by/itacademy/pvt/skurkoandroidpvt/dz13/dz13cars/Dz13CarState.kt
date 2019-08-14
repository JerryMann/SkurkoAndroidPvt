package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi

sealed class Dz13CarState {
    class GetAll(val poiList: List<Poi>) : Dz13CarState()
    class GetOneCar(val poi: Poi) : Dz13CarState()
    object Error : Dz13CarState()
}