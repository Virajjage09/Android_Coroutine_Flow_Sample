package com.codervj.network

import android.content.Context
import com.codervj.network.NetworkConstants.BASE_URL
import com.codervj.network.NetworkConstants.CACHE_SIZE_BYTES
import com.codervj.network.NetworkConstants.CONNECTION_TIMEOUT
import com.codervj.network.NetworkConstants.READ_TIMEOUT
import com.codervj.network.NetworkConstants.WRITE_TIMEOUT
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        interceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //Add your network headers here
            it.proceed(requestBuilder.build())
        }
    }

//    @Provides
//    @Singleton
//    fun provideCache(context: Context): Cache {
//        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
//        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
//    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }

}