package by.itacademy.pvt.skurkoandroidpvt.dz9

fun provideCarRepository(): CarRepository {

    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                NetProvider.provideOhHttp(),
                NetProvider.provideGson()
            )
        )
    )
}