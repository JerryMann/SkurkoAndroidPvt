package by.itacademy.pvt.skurkoandroidpvt.dz9

import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CarResponse
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi
import io.reactivex.Single

interface CarRepository {

    fun getCarByCoordinate(
        params: CoordinateParams,
        onSuccess: (List<Poi>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getCars(params: CoordinateParams): Single<CarResponse>
}
