package com.aubynsamuel.cardit.di

import android.content.Context
import com.aubynsamuel.cardit.data.local.AppDatabase
import com.aubynsamuel.cardit.data.local.ContactDao
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideContactDao(@ApplicationContext appContext: Context): ContactDao {
        return AppDatabase.getDatabase(appContext).contactDao()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}