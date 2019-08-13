package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CarResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Dz13Api {
    @GET("t/fake-api/car-service.php")
    fun getCarsByCoord(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Single<CarResponse>
}