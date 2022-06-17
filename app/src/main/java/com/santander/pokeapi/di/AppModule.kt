package com.santander.pokeapi.di

import com.santander.pokeapi.common.Constants
import com.santander.pokeapi.data.remote.PokeApi
import com.santander.pokeapi.data.repository.PokemonRepositoryImpl
import com.santander.pokeapi.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providePokeApi(): PokeApi {
        var okHttpClient: OkHttpClient?
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokeApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokeApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}