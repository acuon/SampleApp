package com.acuon.sampleapp.di

import android.app.Application
import androidx.room.Room
import com.acuon.sampleapp.data.local.UsersDao
import com.acuon.sampleapp.data.local.UsersDatabase
import com.acuon.sampleapp.data.remote.ApiClass
import com.acuon.sampleapp.data.repository.HomeRepositoryImpl
import com.acuon.sampleapp.domain.repository.IHomeRepository
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
import com.acuon.sampleapp.BuildConfig

/**
 * Dagger Hilt module that provides various dependencies for the application
 * This module includes providers for Retrofit, OkHttpClient, Room database, API services and repository implementations
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * provides the ApiClass service using Retrofit
     */
    @Provides
    @Singleton
    fun provideApiClass(retrofit: Retrofit): ApiClass {
        return retrofit.create(ApiClass::class.java)
    }

    /**
     * provides the implementation of the Home repository
     */
    @Provides
    @Singleton
    fun provideHomeRepository(
        apiService: ApiClass,
        usersDao: UsersDao,
    ): IHomeRepository {
        return HomeRepositoryImpl(apiService, usersDao)
    }


    /**
     * provides the UsersDao for accessing users data in the database
     */
    @Provides
    @Singleton
    fun provideUsersDao(database: UsersDatabase): UsersDao {
        return database.getUsersDao()
    }

    /**
     * provides the UsersDatabase instance using Room
     */
    @Provides
    @Singleton
    fun provideUsersDatabase(application: Application): UsersDatabase {
        return Room.databaseBuilder(
            application,
            UsersDatabase::class.java,
            "UsersDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    /**
     * provides the Retrofit instance with a base URL and Gson converter factory
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    /**
     * provides the OkHttpClient instance with a logging interceptor
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    /**
     * provides the HttpLoggingInterceptor instance for logging network requests and responses
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        HttpLoggingInterceptor().level
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
