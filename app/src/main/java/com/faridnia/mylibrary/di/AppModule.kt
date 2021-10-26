package com.faridnia.mylibrary.di

import android.app.Application
import androidx.room.Room
import com.faridnia.mylibrary.data.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback : BooksDatabase.Callback) =
        Room.databaseBuilder(application, BooksDatabase::class.java, "book_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideBookDao(db: BooksDatabase) = db.booksDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope