package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import by.itacademy.pvt.skurkoandroidpvt.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetProvider {

    private var api: Dz13Api? = null

    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    fun provideOkHttp(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            okHttpBuilder.addInterceptor(logging)
        }

        return okHttpBuilder.build()
    }

    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun provideApi(retrofit: Retrofit): Dz13Api {
        if (api == null) {
            api = retrofit.create(Dz13Api::class.java)
        }
        return api!!
    }
}