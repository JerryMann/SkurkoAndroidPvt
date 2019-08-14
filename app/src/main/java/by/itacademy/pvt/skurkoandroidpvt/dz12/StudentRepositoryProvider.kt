package by.itacademy.pvt.skurkoandroidpvt.dz12

fun provideStudentRepository(): StudentRepository {
    return StudentRepositoryRemote(
        StudentNetProvider.provideStudentApi(
            StudentNetProvider.provideRetrofit(
                "https://api.backendless.com/6DFD8ECD-1525-F37D-FF1E-90C2D2EF1400/9A83A3EB-C769-8D13-FF97-1B201844D500/",
                StudentNetProvider.provideOhHttp(),
                StudentNetProvider.provideGson()
            )
        )
    )
}