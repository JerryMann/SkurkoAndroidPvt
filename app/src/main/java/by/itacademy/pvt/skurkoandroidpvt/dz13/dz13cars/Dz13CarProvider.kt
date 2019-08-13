package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

fun provideCarRepository(): CarRepository {
    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}