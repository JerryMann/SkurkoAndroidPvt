package by.itacademy.pvt.skurkoandroidpvt.dz12

import by.itacademy.pvt.skurkoandroidpvt.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object StudentNetProvider {

    private var api: StudentApi? = null

    fun provideGson(): Gson {
        val gson = GsonBuilder()
            .create()

        return gson
    }

    fun provideOhHttp(): OkHttpClient {

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            okHttpBuilder.addInterceptor(logging)
        }

        val okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }

    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    fun provideStudentApi(retrofit: Retrofit): StudentApi {

        if (api == null) {
            api = retrofit.create<StudentApi>(StudentApi::class.java)
        }
        return api!!
    }
}