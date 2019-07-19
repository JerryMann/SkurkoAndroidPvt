package by.itacademy.pvt.skurkoandroidpvt.dz9

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import by.itacademy.pvt.skurkoandroidpvt.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

object NetProvider {

    private var api: Api? = null

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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    fun provideApi(retrofit: Retrofit): Api {

        if (api == null) {
            api = retrofit.create<Api>(Api::class.java)
        }
        return api!!
    }
}