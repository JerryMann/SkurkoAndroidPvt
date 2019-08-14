package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CarResponse
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import io.reactivex.Single

interface CarRepository {
    fun getCarByCoord(params: CoordinateParams): Single<CarResponse>
}