package itis.ru.insultgenerator.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import itis.ru.insultgenerator.InsultGeneratorApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {
    @Provides
    fun provideBaseUrl() = "https://evilinsult.com/"

    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(coroutineCallAdapterFactory)
        .build()

    @Provides
    fun provideInsultService(retrofit: Retrofit): InsultGeneratorApi =
        retrofit.create(InsultGeneratorApi::class.java)
}
