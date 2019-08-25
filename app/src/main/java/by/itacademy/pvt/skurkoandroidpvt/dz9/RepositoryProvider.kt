package by.itacademy.pvt.skurkoandroidpvt.dz9

import by.itacademy.pvt.skurkoandroidpvt.app.App
import by.itacademy.pvt.skurkoandroidpvt.dz15.AppDatabase

fun provideCarRepository(): CarRepository {

    val poiDao = AppDatabase.getInstance(App.instance).getPoiDao()

    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                NetProvider.provideOhHttp(),
                NetProvider.provideGson()
            )
        ), poiDao
    )
}