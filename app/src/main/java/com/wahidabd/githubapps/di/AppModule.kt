package com.wahidabd.githubapps.di

import com.wahidabd.githubapps.BuildConfig
import com.wahidabd.githubapps.core.SafeCall
import com.wahidabd.githubapps.data.repository.UserRepository
import com.wahidabd.githubapps.data.service.GithubService
import com.wahidabd.githubapps.data.source.GithubDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        Interceptor{
            val newReq = it.request()
                .newBuilder()
                .addHeader("Authorization", "token${BuildConfig.API_KEY}")
                .build()
            it.proceed(newReq)
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

    @Provides
    @Singleton
    fun provideSafeCall(): SafeCall = SafeCall()

    @Provides
    @Singleton
    fun provideUserRepository(data: GithubDataSource): UserRepository =
        UserRepository(data)

}