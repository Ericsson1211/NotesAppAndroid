package sk.globing.common.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sk.globing.common.BuildConfig

const val API_BASE_URL = "https://raw.githubusercontent.com/Ericsson1211/NotesAppAndroid/"

val apiModule = module {
    factory { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        loggingInterceptor.apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
    } else {
        loggingInterceptor.apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE }
    }

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .callFactory(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}