package by.itacademy.pvt.skurkoandroidpvt.dz9

import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CarResponse
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// CRUD Create Read Update Delete
class CarRepositoryRemote(private val api: Api) : CarRepository {

    override fun getCarByCoordinate(
        params: CoordinateParams,
        onSuccess: (List<Poi>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        api.getCarsByCoord(
            params.coord1.latitude, params.coord1.longitude,
            params.coord2.latitude, params.coord2.longitude
        ).enqueue(object : Callback<CarResponse> {
            override fun onFailure(call: Call<CarResponse>?, throwable: Throwable?) {
                onFailure(throwable!!)
            }

            override fun onResponse(call: Call<CarResponse>?, response: Response<CarResponse>?) {
                onSuccess(response!!.body()?.poiList ?: emptyList())
            }
        })
    }
}
