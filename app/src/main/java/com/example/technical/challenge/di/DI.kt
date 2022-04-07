package com.example.technical.challenge.di

import com.example.technical.challenge.data.network.NetworkService
import com.example.technical.challenge.data.repositories.DefaultProductListingRepository
import com.example.technical.challenge.data.repositories.DefaultScheduleListingRepository
import com.example.technical.challenge.domain.reprositories.ProductListingRepository
import com.example.technical.challenge.domain.reprositories.ScheduleListingRepository
import com.example.technical.challenge.domain.usecases.product.ProductsListUseCase
import com.example.technical.challenge.domain.usecases.product.ProductsListUseCaseImp
import com.example.technical.challenge.domain.usecases.schedule.ScheduleListUseCaseImp
import com.example.technical.challenge.domain.usecases.schedule.ScheduleListUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class DI {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkService.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : NetworkService{
        return retrofit.create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductListingRepository(defaultProductListingRepository: DefaultProductListingRepository) : ProductListingRepository
        = defaultProductListingRepository

    @Singleton
    @Provides
    fun provideScheduleListingRepository(defaultScheduleListingRepository: DefaultScheduleListingRepository) : ScheduleListingRepository
            = defaultScheduleListingRepository

    @Provides
    fun provideScheduleListUseCase(scheduleListUseCaseImp: ScheduleListUseCaseImp) : ScheduleListUseCase
            = scheduleListUseCaseImp

    @Provides
    fun provideProductsListUseCase(productsListUseCaseImp: ProductsListUseCaseImp) : ProductsListUseCase
            = productsListUseCaseImp

}