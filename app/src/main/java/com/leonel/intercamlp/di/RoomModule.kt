package com.leonel.intercamlp.di

import android.content.Context
import androidx.room.Room
import com.leonel.intercamlp.database.IntercamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "intercam_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, IntercamDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideUSerDao(db: IntercamDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun provideFavoritosDao(db: IntercamDatabase) = db.getFavoritosDao()

}