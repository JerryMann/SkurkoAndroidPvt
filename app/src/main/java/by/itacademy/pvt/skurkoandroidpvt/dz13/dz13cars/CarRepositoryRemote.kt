package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CarResponse
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import io.reactivex.Single

class CarRepositoryRemote(private val api: Dz13Api) : CarRepository {
    override fun getCarByCoord(params: CoordinateParams): Single<CarResponse> {
        return api.getCarsByCoord(
            params.coord1.latitude,
            params.coord1.longitude,
            params.coord2.latitude,
            params.coord2.longitude
        )
    }
}